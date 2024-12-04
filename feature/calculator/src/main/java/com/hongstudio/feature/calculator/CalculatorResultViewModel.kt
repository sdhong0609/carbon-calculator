package com.hongstudio.feature.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.model.CalculatorResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorResultUiState.DEFAULT)
    val uiState: StateFlow<CalculatorResultUiState> = _uiState.asStateFlow()

    private val total =
        savedStateHandle.toRoute<Route.CalculatorResult>(TypeMap.calculatorDataTypeMap).calculatorData.total

    init {
        _uiState.value = CalculatorResultUiState(total = total)
    }
}