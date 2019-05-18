package com.darwish.roomie.presentation.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darwish.roomie.presentation.main.MainActivity
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
