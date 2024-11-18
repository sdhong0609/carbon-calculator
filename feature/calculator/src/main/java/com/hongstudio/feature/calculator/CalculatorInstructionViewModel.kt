package com.hongstudio.feature.calculator

import androidx.lifecycle.ViewModel
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.feature.calculator.model.CalculatorInstructionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorInstructionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorInstructionUiState())
    val uiState: StateFlow<CalculatorInstructionUiState> = _uiState.asStateFlow()

    fun onElectricityCheckedChange(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isElectricityChecked = isChecked)
    }

    fun onGasCheckedChange(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isGasChecked = isChecked)
    }

    fun onWaterCheckedChange(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isWaterChecked = isChecked)
    }

    fun onTrashCheckedChange(isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(isTrashChecked = isChecked)
    }

    fun createCalculatorSelected() = CalculatorSelected(
        isElectricitySelected = _uiState.value.isElectricityChecked,
        isGasSelected = _uiState.value.isGasChecked,
        isWaterSelected = _uiState.value.isWaterChecked,
        isTrashSelected = _uiState.value.isTrashChecked
    )
}