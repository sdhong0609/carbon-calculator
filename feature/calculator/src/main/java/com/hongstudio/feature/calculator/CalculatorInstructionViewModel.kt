package com.hongstudio.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongstudio.core.model.CalculatorType
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
                val selectedCalculators = _uiState.value.calculatorCheckboxes
                    .filter { it.isChecked }
                    .map { it.type }
                _event.emit(CalculatorInstructionEvent.NavigateToCalculator(selectedCalculators))
            } else {
                _event.emit(CalculatorInstructionEvent.ShowSnackBar(R.string.please_select_item))
            }
        }
    }
}