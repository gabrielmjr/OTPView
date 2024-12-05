package com.kevinschildhorn.otpview.sample

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevinschildhorn.otpview.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        initTestingUI()
        initOTPView()
    }

    private fun initTestingUI() {
        activityMainBinding.apply {
            copyButton.setOnClickListener {
                otpView.copyText()
            }
            pasteButton.setOnClickListener {
                otpView.pasteText()
            }
            fillButton.setOnClickListener {
                otpView.setText("ABCDEF_EXTRA")
            }
            clearButton.setOnClickListener {
                otpView.clearText(false)
            }
            continueButton.setOnClickListener {
                val text = otpView.getStringFromFields()
                Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initOTPView() {
        activityMainBinding.apply {
            otpView.apply {
                setOnFinishListener {
                    Log.i("MainActivity", it)
                    Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                }

                setOnCharacterUpdatedListener {
                    if (it)
                        Log.i("MainActivity", "The view is filled")
                    else
                        Log.i("MainActivity", "The view is NOT Filled")
                    continueButton.isEnabled = it
                    //otp_view.isFilled()
                }
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val width = displayMetrics.widthPixels

                fitToWidth(width)
            }
        }
    }
}