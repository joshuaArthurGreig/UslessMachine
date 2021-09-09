package com.example.uslessmachine

import android.content.IntentSender
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {

    lateinit var uselessSwitch : Switch
    lateinit var lookBusy : Button
    lateinit var selfDestruct: Button
    lateinit var layout : ConstraintLayout
    lateinit var mainUI : Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        uselessSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                Toast.makeText(MainActivity@this, "Switch goes on", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(MainActivity@this, "Switch goes off", Toast.LENGTH_SHORT).show()
            }

            startSwitchTimer()
        }

        selfDestruct.setOnClickListener() {
            startDestructTimer()
        }

        lookBusy.setOnClickListener() {
            mainUI.visibility = View.GONE
        }

    }

    private fun wireWidgets() {
        uselessSwitch = findViewById(R.id.button_main_uselessSwitch)
        lookBusy = findViewById(R.id.button_main_busy)
        selfDestruct = findViewById(R.id.button_main_destruct)
        layout = findViewById(R.id.main_layout)
        mainUI = findViewById(R.id.group_main_mainUI)
    }

    private fun startSwitchTimer() {
        object : CountDownTimer(3000,100) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                uselessSwitch.isChecked = false
            }
        }.start()
    }

    private fun startDestructTimer() {
        var yellowtrue = false
        object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                selfDestruct.text = "T-MINUS:" + (1 +(millisUntilFinished / 1000))
                if(!yellowtrue) {
                    layout.setBackgroundColor(Color.YELLOW)
                    yellowtrue = true
                } else {
                    layout.setBackgroundColor(Color.RED)
                    yellowtrue = false
                }
            }

            override fun onFinish() {
                finish()
            }
        }.start()
    }

}

