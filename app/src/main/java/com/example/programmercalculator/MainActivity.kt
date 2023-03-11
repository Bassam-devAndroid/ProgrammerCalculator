package com.example.programmercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var inputRadioGroup: RadioGroup
    private lateinit var outputRadioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        inputEditText = findViewById(R.id.inputEditText)
        outputTextView = findViewById(R.id.outputTextView)
        inputRadioGroup = findViewById(R.id.inputRadioGroup)
        outputRadioGroup = findViewById(R.id.outputRadioGroup)

        inputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convert()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        inputRadioGroup.setOnCheckedChangeListener { _, _ ->
            convert()
        }

        outputRadioGroup.setOnCheckedChangeListener { _, _ ->
            convert()
        }
    }

    private fun convert() {
        val input = inputEditText.text.toString()

        if (input.isBlank()) {
            outputTextView.text = ""
            return
        }

        val inputBase = getInputBase()
        val outputBase = getOutputBase()

        val value = when (inputBase) {
            2 -> input.toLongOrNull(2)
            8 -> input.toLongOrNull(8)
            10 -> input.toLongOrNull()
            16 -> input.toLongOrNull(16)
            else -> null
        }

        if (value == null) {
            outputTextView.text = ""
            return
        }

        val output = when (outputBase) {
            2 -> value.toString(2)
            8 -> value.toString(8)
            10 -> value.toString()
            16 -> value.toString(16)
            else -> ""
        }

        outputTextView.text = output
    }

    private fun getInputBase(): Int {
        val radioButton = findViewById<RadioButton>(inputRadioGroup.checkedRadioButtonId)
        return when (radioButton.id) {
            R.id.Binary -> 2
            R.id.octal -> 8
            R.id.Decimal -> 10
            R.id.Hexadecimal -> 16
            else -> 10
        }
    }

    private fun getOutputBase(): Int {
        val radioButton = findViewById<RadioButton>(outputRadioGroup.checkedRadioButtonId)
        return when (radioButton.id) {
            R.id.output_Binary -> 2
            R.id.output_octal -> 8
            R.id.output_Decimal -> 10
            R.id.output_Hexadecimal -> 16
            else -> 10
        }
    }
}