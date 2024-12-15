package com.hongstudio.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.feature.calculator.model.CalculatorInstructionEvent
import com.hongstudio.feature.calculator.model.CalculatorInstructionUiState
import com.hongstudio.feature.calculator.model.CalculatorType
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
class CalculatorInstructionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorInstructionUiState())
    val uiState: StateFlow<CalculatorInstructionUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CalculatorInstructionEvent>()
    val event = _event.asSharedFlow()

    fun toggleCalculatorCheckbox(calculatorType: CalculatorType) {
        _uiState.update {
            it.copy(
                calculatorCheckboxes = it.calculatorCheckboxes.map {
                    if (it.type == calculatorType) {
                        it.copy(isChecked = !it.isChecked)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun onStartCalculatorClick() {
        viewModelScope.launch {
            if (_uiState.value.isAnyChecked) {
                _event.emit(CalculatorInstructionEvent.NavigateToCalculator(createCalculatorSelected()))
            } else {
                _event.emit(CalculatorInstructionEvent.ShowSnackBar(R.string.please_select_item))
            }
        }
    }

    private fun createCalculatorSelected() = CalculatorSelected(
        isElectricitySelected = _uiState.value.calculatorCheckboxes.find { it.type == CalculatorType.ELECTRICITY }?.isChecked == true,
        isGasSelected = _uiState.value.calculatorCheckboxes.find { it.type == CalculatorType.GAS }?.isChecked == true,
        isWaterSelected = _uiState.value.calculatorCheckboxes.find { it.type == CalculatorType.WATER }?.isChecked == true,
        isTrashSelected = _uiState.value.calculatorCheckboxes.find { it.type == CalculatorType.TRASH }?.isChecked == true
    )
}