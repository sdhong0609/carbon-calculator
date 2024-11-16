package com.hongstudio.feature.calculator

import androidx.lifecycle.ViewModel
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.feature.calculator.model.CalculatorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun onElectricityChange(electricityInput: String) {
        _uiState.value = _uiState.value.copy(electricityInput = electricityInput)
    }

    fun onGasChange(gasInput: String) {
        _uiState.value = _uiState.value.copy(gasInput = gasInput)
    }

    fun onWaterChange(waterInput: String) {
        _uiState.value = _uiState.value.copy(waterInput = waterInput)
    }

    fun onTrashChange(trashInput: String) {
        _uiState.value = _uiState.value.copy(trashInput = trashInput)
    }

    fun onResetClick() {
        _uiState.value = CalculatorUiState()
    }

    fun calculatorData() =
        CalculatorData.create(
            electricity = _uiState.value.electricityInput.ifEmpty { "0" }.toDouble(),
            gas = _uiState.value.gasInput.ifEmpty { "0" }.toDouble(),
            water = _uiState.value.waterInput.ifEmpty { "0" }.toDouble(),
            trash = _uiState.value.trashInput.ifEmpty { "0" }.toDouble()
        )
}