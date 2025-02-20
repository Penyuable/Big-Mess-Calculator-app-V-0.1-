package com.brydev.bigmess

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    private lateinit var inputField: TextView
    private lateinit var resultField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputField)
        resultField = findViewById(R.id.resultField)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnDot
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                inputField.append((it as Button).text)
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            inputField.text = ""
            resultField.text = ""
        }

        findViewById<Button>(R.id.btnBackspace).setOnClickListener {
            val text = inputField.text.toString()
            if (text.isNotEmpty()) {
                inputField.text = text.dropLast(1)
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            try {
                val expression = ExpressionBuilder(inputField.text.toString()).build()
                val result = expression.evaluate()

                resultField.text = if (result % 1 == 0.0) {
                    result.toInt().toString()
                } else {
                    String.format("%.2f", result).replace(",", ".") // Jika desimal, format 2 angka di belakang koma
                }
            } catch (e: Exception) {
                resultField.text = "Error"
            }
        }

    }
}