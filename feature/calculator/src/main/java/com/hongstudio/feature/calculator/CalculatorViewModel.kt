package com.hongstudio.feature.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.model.CalculatorEvent
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

    private val _uiState = MutableStateFlow(CalculatorUiState.DEFAULT)
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CalculatorEvent>()
    val event = _event.asSharedFlow()

    private val calculatorSelected =
        savedStateHandle.toRoute<Route.Calculator>(TypeMap.calculatorSelectedTypeMap).calculatorSelected

    init {
        _uiState.value = CalculatorUiState(
            isElectricityVisible = calculatorSelected.isElectricitySelected,
            isGasVisible = calculatorSelected.isGasSelected,
            isWaterVisible = calculatorSelected.isWaterSelected,
            isTrashVisible = calculatorSelected.isTrashSelected
        )
    }

    fun onElectricityChange(electricityInput: String) {
        _uiState.update { it.copy(electricityInput = electricityInput) }
    }

    fun onGasChange(gasInput: String) {
        _uiState.update { it.copy(gasInput = gasInput) }
    }

    fun onWaterChange(waterInput: String) {
        _uiState.update { it.copy(waterInput = waterInput) }
    }

    fun onTrashChange(trashInput: String) {
        _uiState.update { it.copy(trashInput = trashInput) }
    }

    fun onResetClick() {
        _uiState.value = CalculatorUiState(
            isElectricityVisible = calculatorSelected.isElectricitySelected,
            isGasVisible = calculatorSelected.isGasSelected,
            isWaterVisible = calculatorSelected.isWaterSelected,
            isTrashVisible = calculatorSelected.isTrashSelected
        )
    }

    fun onResultClick() {
        viewModelScope.launch {
            if (_uiState.value.isAllInputFilled) {
                _event.emit(CalculatorEvent.NavigateToCalculatorResult(createCalculatorData()))
            } else {
                _event.emit(CalculatorEvent.ShowSnackBar("모든 입력란을 채워주세요"))
            }
        }
    }

    private fun createCalculatorData() = CalculatorData(
        electricity = _uiState.value.electricityInput.ifEmpty { "0" }.toDouble(),
        gas = _uiState.value.gasInput.ifEmpty { "0" }.toDouble(),
        water = _uiState.value.waterInput.ifEmpty { "0" }.toDouble(),
        trash = _uiState.value.trashInput.ifEmpty { "0" }.toDouble()
    )
}