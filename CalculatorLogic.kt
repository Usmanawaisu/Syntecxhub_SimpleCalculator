package com.example.simplecalculator

object CalculatorLogic {

    fun calculate(first: Double, second: Double, operator: String): String {
        return when (operator) {
            "+" -> (first + second).toString()
            "-" -> (first - second).toString()
            "*" -> (first * second).toString()
            "/" -> {
                if (second == 0.0) {
                    "Cannot divide by zero"
                } else {
                    (first / second).toString()
                }
            }
            else -> "Error"
        }
    }
}