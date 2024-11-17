package com.hongstudio.feature.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.navigation.calculatorDataTypeMap
import com.hongstudio.feature.calculator.model.CalculatorResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalculatorResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorResultUiState())
    val uiState: StateFlow<CalculatorResultUiState> = _uiState.asStateFlow()

    private val calculatorData = savedStateHandle.toRoute<CalculatorData>(calculatorDataTypeMap)

    init {
        val (electricity, gas, water, trash) = calculatorData
        val total = String.format(
            Locale.getDefault(),
            "%,.1f",
            electricity * 0.4781 + gas * 2.176 + water * 0.237 + trash * 0.9529
        )

        _uiState.value = _uiState.value.copy(total = total)
    }
}