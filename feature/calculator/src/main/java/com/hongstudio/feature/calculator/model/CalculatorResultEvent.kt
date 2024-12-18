package com.hongstudio.feature.calculator.model

sealed interface CalculatorResultEvent {
    data object PopUntilCalculatorInstruction : CalculatorResultEvent
}