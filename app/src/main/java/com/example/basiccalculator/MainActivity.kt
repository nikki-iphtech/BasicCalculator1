package com.example.basiccalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val output = findViewById<EditText>(R.id.output)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // Set button click listeners using a single function
        btnAdd.setOnClickListener { calculate(num1, num2, output, "+") }
        btnSubtract.setOnClickListener { calculate(num1, num2, output, "-") }
        btnMultiply.setOnClickListener { calculate(num1, num2, output, "*") }
        btnDivide.setOnClickListener { calculate(num1, num2, output, "/") }

        // Clear button to reset all fields
        btnClear.setOnClickListener {
            num1.text.clear()
            num2.text.clear()
            output.setText("")
        }
    }

    // Function to perform calculations
    private fun calculate(num1: EditText, num2: EditText, output: EditText, operation: String) {
        val n1 = num1.text.toString().toDoubleOrNull()
        val n2 = num2.text.toString().toDoubleOrNull()

        if (n1 == null || n2 == null) {
            output.setText("Invalid Input")
            return
        }

        val result = when (operation) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else "Cannot Divide by 0"
            else -> "Error"
        }

        output.setText(result.toString())
    }

    }
