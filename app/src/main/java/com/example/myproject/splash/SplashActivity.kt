 package com.example.myproject.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myproject.R
import com.example.myproject.main.MainActivity

 //Création de la classe SplashActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Affiche la vue Splash
        setContentView(R.layout.activity_splash)
        //Pendant 3 secondes
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }, 3000)
    }
}