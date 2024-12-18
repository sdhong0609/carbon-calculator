package com.hongstudio.feature.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.model.CalculatorResultEvent
import com.hongstudio.feature.calculator.model.CalculatorResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorResultUiState())
    val uiState: StateFlow<CalculatorResultUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CalculatorResultEvent>()
    val event = _event.asSharedFlow()

    private val inputCompletedCalculators =
        savedStateHandle.toRoute<Route.CalculatorResult>(TypeMap.inputCompletedCalculatorsTypeMap).inputCompletedCalculators

    init {
        _uiState.value = CalculatorResultUiState(total = inputCompletedCalculators.map {
            it.input * it.type.multiply
        }.sum())
    }

    fun onRestartClick() {
        viewModelScope.launch {
            _event.emit(CalculatorResultEvent.PopUntilCalculatorInstruction)
        }
    }
}