package com.example.movieapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.ui.home.HomeActivity
import com.example.movieapp.ui.login.LoginActivity

/**
 * Create by Linh Le H. M. on 11/11/20
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handleChangeActivityDelay()
    }

    private fun handleChangeActivityDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}
