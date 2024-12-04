package com.hongstudio.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.feature.calculator.model.CalculatorInstructionEvent
import com.hongstudio.feature.calculator.model.CalculatorInstructionUiState
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

    private val _uiState = MutableStateFlow(CalculatorInstructionUiState.DEFAULT)
    val uiState: StateFlow<CalculatorInstructionUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CalculatorInstructionEvent>()
    val event = _event.asSharedFlow()

    fun onElectricityCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isElectricityChecked = isChecked) }
    }

    fun onGasCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isGasChecked = isChecked) }
    }

    fun onWaterCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isWaterChecked = isChecked) }
    }

    fun onTrashCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isTrashChecked = isChecked) }
    }

    fun onStartCalculatorClick() {
        viewModelScope.launch {
            val calculatorSelected = createCalculatorSelected()
            if (calculatorSelected.isAnySelected) {
                _event.emit(CalculatorInstructionEvent.NavigateToCalculator(createCalculatorSelected()))
            } else {
                _event.emit(CalculatorInstructionEvent.ShowSnackBar("계산할 항목을 선택해 주세요"))
            }
        }
    }

    fun createCalculatorSelected() = CalculatorSelected(
        isElectricitySelected = _uiState.value.isElectricityChecked,
        isGasSelected = _uiState.value.isGasChecked,
        isWaterSelected = _uiState.value.isWaterChecked,
        isTrashSelected = _uiState.value.isTrashChecked
    )
}