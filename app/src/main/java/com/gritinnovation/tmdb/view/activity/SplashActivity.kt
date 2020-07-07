package com.gritinnovation.tmdb.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.gritinnovation.tmdb.R
import com.gritinnovation.tmdb.databinding.ActivitySplashBinding
import com.gritinnovation.tmdb.view.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
