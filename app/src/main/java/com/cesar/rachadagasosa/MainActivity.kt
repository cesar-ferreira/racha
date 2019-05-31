package com.cesar.rachadagasosa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    /**
     * Constant used to specify distance from virtus to dallas park.
     */
    companion object {
        private const val DISTANCE_TO_MY_HOUSE: String = "5"
        private const val FUEL_CONSUMPTION: String = "6.5"

        private const val VALUE_OF_THE_DAY: Int = 10
    }


    private var isConsumptionValid: Boolean = false
    private var isDistanceValid: Boolean = false
    private var isFuelValid: Boolean = false
    private var isDayValid: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Companion.DISTANCE_TO_MY_HOUSE

        refresh()
        autoComplete()
        calculatesValue()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refresh () {

        refresh.setOnClickListener {
            valueDistance.setText("")
            valueConsumption.setText("")
            valueFuel.setText("")
            valueDays.setProgress(1F)
            valueResult.text = ""
            validatingForm()


        }



    }

    private fun autoComplete() {
        valueDefault.setOnClickListener {

            valueDistance.setText(DISTANCE_TO_MY_HOUSE)
            valueConsumption.setText(FUEL_CONSUMPTION)
            validatingForm()

        }

    }

    private fun calculatesValue() {
        validatingForm()
//        when {
//            validatingForm() -> {
//                enableButton()
//            }
//            else -> disableButton()
//        }

        button.setOnClickListener {
            valueResult.text = (calculateTheFuelPriceToBePaid()/2).toString()

        }

    }


    /**
     * Calcula quantos quilometros foram rodados
     */
    private fun calculateMileageToPay(): Int {

        return VALUE_OF_THE_DAY * valueDays.progress

    }

    /**
     * Calcula a quantidade de litros de combustivel pelos quilometros rodados
     */
    private fun calculateQuantityOfLitersOfFuelPayable(): Double {



        return calculateMileageToPay() / valueConsumption.text.toString().toDouble()

    }

    /**
     * Calcula a quantidade total a pagar de acordo com o valor do combustivel
     */
    private fun calculateTheFuelPriceToBePaid(): Double {

        return calculateQuantityOfLitersOfFuelPayable() * valueFuel.text.toString().toDouble()

    }

    private fun validatingDistanceValue() {

        if(valueDistance.text.toString() == ""){
            isDistanceValid = false
            valueDistance.error = "O valor não pode ser vazio"
        } else {
            isDistanceValid = true
            valueDistance.error = null
        }

    }

    private fun validatingConsumptionValue() {

        if(valueConsumption.text.toString() == ""){
            isConsumptionValid = false
            valueConsumption.error = "O valor não pode ser vazio"
        } else {
            isConsumptionValid = true
            valueConsumption.error = null
        }

    }

    private fun validatingFuelValue() {

        if(valueFuel.text.toString() == ""){
            isFuelValid = false
            valueFuel.error = "O valor não pode ser vazio"
        } else {
            isFuelValid = true
            valueFuel.error = null
        }

    }


    private fun validatingForm(): Boolean{

        validatingDistanceValue()
        validatingConsumptionValue()
        validatingFuelValue()

        var isValid = true

        if(!isDistanceValid){
            isValid = false
        }
        if(!isConsumptionValid){
            isValid = false
        }
        if(!isFuelValid){
            isValid = false
        }


        return isValid
    }

    private fun disableButton() {

        button.isEnabled = false

    }

    private fun enableButton() {

        button.isEnabled = true

    }
}
