package com.vyconsulting.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.calculate_button).setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip(){
        var cost: Editable  = findViewById<EditText>(R.id.cost_of_service).text

        val checkedBtnId = findViewById<RadioGroup>(R.id.tip_options).checkedRadioButtonId

        val tipPercentage = when (checkedBtnId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip : Double = 0.0

        if(! cost.isEmpty()){
            tip = tipPercentage * cost.toString().toDouble()
            Toast.makeText(this, "${tip}", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Coast of service is empty", Toast.LENGTH_SHORT).show()
        }

        if (findViewById<Switch>(R.id.round_up_switch).isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        findViewById<TextView>(R.id.tip_result).text = getString(R.string.tip_amount, formattedTip)

    }
}