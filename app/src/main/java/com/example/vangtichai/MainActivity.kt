package com.example.vangtichai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var amountTextView: TextView
    private lateinit var note500: TextView
    private lateinit var note100: TextView
    private lateinit var note50: TextView
    private lateinit var note20: TextView
    private lateinit var note10: TextView
    private lateinit var note5: TextView
    private lateinit var note2: TextView
    private lateinit var note1: TextView

    private var amount: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountTextView = findViewById(R.id.amountTextView)
        note500 = findViewById(R.id.note500)
        note100 = findViewById(R.id.note100)
        note50 = findViewById(R.id.note50)
        note20 = findViewById(R.id.note20)
        note10 = findViewById(R.id.note10)
        note5 = findViewById(R.id.note5)
        note2 = findViewById(R.id.note2)
        note1 = findViewById(R.id.note1)

        if (savedInstanceState != null) {
            amount = savedInstanceState.getLong("amount")
            updateUI()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("amount", amount)
    }

    fun onDigitClick(view: View) {
        val button = view as Button
        val digit = button.text.toString().toInt()

        if (amount == 0L) {
            amount = digit.toLong()
        } else {
            amount = (amount * 10) + digit
        }
        updateUI()
    }

    fun onClearClick(view: View) {
        amount = 0
        updateUI()
    }

    private fun updateUI() {
        amountTextView.text = amount.toString()
        calculateChange(amount)
    }

    private fun calculateChange(totalAmount: Long) {
        var remainingAmount = totalAmount

        val count500 = remainingAmount / 500
        remainingAmount %= 500
        note500.text = count500.toString()

        val count100 = remainingAmount / 100
        remainingAmount %= 100
        note100.text = count100.toString()

        val count50 = remainingAmount / 50
        remainingAmount %= 50
        note50.text = count50.toString()

        val count20 = remainingAmount / 20
        remainingAmount %= 20
        note20.text = count20.toString()

        val count10 = remainingAmount / 10
        remainingAmount %= 10
        note10.text = count10.toString()

        val count5 = remainingAmount / 5
        remainingAmount %= 5
        note5.text = count5.toString()

        val count2 = remainingAmount / 2
        remainingAmount %= 2
        note2.text = count2.toString()

        note1.text = remainingAmount.toString()
    }
}
