    package com.jyotsna.userinput

    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.TextView
    import android.widget.EditText
    import android.widget.Spinner
    import androidx.appcompat.app.AppCompatActivity
    import com.jyotsna.userinput.R

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val textViewSelectTipPercentage: TextView = findViewById(R.id.textViewSelectTipPercentage)
            val buttonCalculateTip: Button = findViewById(R.id.buttonCalculateTip)
            val textViewTipAmount: TextView = findViewById(R.id.textViewTipAmount)
            val editTextMealCost: EditText = findViewById(R.id.editTextMealCost)
            val spinnerTipPercentage: Spinner = findViewById(R.id.spinnerTipPercentage)

            buttonCalculateTip.setOnClickListener {
                val mealCostStr = editTextMealCost.text.toString()
                val tipPercentageStr = spinnerTipPercentage.selectedItem?.toString()

                if (mealCostStr.isNotEmpty() && !tipPercentageStr.isNullOrEmpty()) {
                    try {
                        val mealCost = mealCostStr.toDouble()
                        val tipPercentage = tipPercentageStr.toInt()
                        val tipAmount = mealCost * (tipPercentage.toDouble() / 100)
                        textViewTipAmount.text = "Tip Amount: $${"%.2f".format(tipAmount)}"

                        // Hide the "Please select a tip percentage" message
                        textViewSelectTipPercentage.visibility = View.GONE
                    } catch (e: NumberFormatException) {
                        textViewTipAmount.text = "Invalid input. Please enter a valid meal cost."
                    }
                } else {
                    if (mealCostStr.isEmpty()) {
                        textViewTipAmount.text = "Please enter meal cost."
                    } else {
                        // Show the "Please select a tip percentage" message
                        textViewSelectTipPercentage.visibility = View.VISIBLE
                        textViewTipAmount.text = ""
                    }
                }
            }
        }
    }
