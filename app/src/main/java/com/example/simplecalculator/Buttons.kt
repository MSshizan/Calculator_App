package com.example.simplecalculator

enum class ButtonType{NUMBER, OPERATOR }

data class  CalculatorButton(val label:String, val type: ButtonType)



object CalculatorButtons {
    val buttonList = listOf(
        CalculatorButton("C", ButtonType.OPERATOR),
        CalculatorButton("(", ButtonType.OPERATOR),
        CalculatorButton(")", ButtonType.OPERATOR),
        CalculatorButton("/", ButtonType.OPERATOR),
        CalculatorButton("1", ButtonType.NUMBER),
        CalculatorButton("2", ButtonType.NUMBER),
        CalculatorButton("3", ButtonType.NUMBER),
        CalculatorButton("*", ButtonType.OPERATOR),
        CalculatorButton("4", ButtonType.NUMBER),
        CalculatorButton("5", ButtonType.NUMBER),
        CalculatorButton("6", ButtonType.NUMBER),
        CalculatorButton("-", ButtonType.OPERATOR),
        CalculatorButton("7", ButtonType.NUMBER),
        CalculatorButton("8", ButtonType.NUMBER),
        CalculatorButton("9", ButtonType.NUMBER),
        CalculatorButton("+", ButtonType.OPERATOR),
        CalculatorButton("AC", ButtonType.OPERATOR),
        CalculatorButton("0", ButtonType.NUMBER),
        CalculatorButton(".", ButtonType.OPERATOR),
        CalculatorButton("=", ButtonType.OPERATOR),
    )
}