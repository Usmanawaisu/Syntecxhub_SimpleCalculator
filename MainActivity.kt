package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var firstNumber = ""
    private var secondNumber = ""
    private var operator = ""
    private var isSecondNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                onNumberClick((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperatorClick("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
    }

    private fun onNumberClick(number: String) {
        if (!isSecondNumber) {
            firstNumber += number
            tvDisplay.text = firstNumber
        } else {
            secondNumber += number
            tvDisplay.text = secondNumber
        }
    }

    private fun onOperatorClick(op: String) {
        if (firstNumber.isNotEmpty()) {
            operator = op
            isSecondNumber = true
        }
    }

    private fun calculateResult() {
        if (firstNumber.isEmpty() || secondNumber.isEmpty()) {
            tvDisplay.text = "Invalid Input"
            return
        }

        val result = CalculatorLogic.calculate(
            firstNumber.toDouble(),
            secondNumber.toDouble(),
            operator
        )

        tvDisplay.text = result
        firstNumber = result
        secondNumber = ""
        operator = ""
        isSecondNumber = false
    }

    private fun clearAll() {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        isSecondNumber = false
        tvDisplay.text = "0"
    }
}