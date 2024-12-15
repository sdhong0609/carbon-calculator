package com.hongstudio.feature.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.model.CalculatorType
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.model.CalculatorEvent
import com.hongstudio.feature.calculator.model.CalculatorTextField
import com.hongstudio.feature.calculator.model.CalculatorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CalculatorEvent>()
    val event = _event.asSharedFlow()

    private val selectedCalculators =
        savedStateHandle.toRoute<Route.Calculator>(TypeMap.selectedCalculatorsTypeMap).selectedCalculators

    init {
        onResetClick()
    }

    fun onInputChange(calculatorType: CalculatorType, input: String) {
        _uiState.update {
            it.copy(
                calculatorTextFields = it.calculatorTextFields.map {
                    if (it.type == calculatorType) {
                        it.copy(input = input)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun onResetClick() {
        _uiState.value = CalculatorUiState(
            calculatorTextFields = selectedCalculators.map {
                CalculatorTextField(
                    type = it,
                    input = ""
                )
            }
        )
    }

    fun onResultClick() {
        viewModelScope.launch {
            if (_uiState.value.isAllInputFilled) {
                _event.emit(CalculatorEvent.NavigateToCalculatorResult(createCalculatorData()))
            } else {
                _event.emit(CalculatorEvent.ShowSnackBar(R.string.please_fill_all_input))
            }
        }
    }

    private fun createCalculatorData() = CalculatorData(
        electricity = 0.0,
        gas = 0.0,
        water = 0.0,
        trash = 0.0
    )
}