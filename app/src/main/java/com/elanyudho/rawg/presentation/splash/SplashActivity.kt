package com.elanyudho.rawg.presentation.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.rawg.databinding.ActivitySplashBinding
import com.elanyudho.rawg.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivityBinding<ActivitySplashBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = { ActivitySplashBinding.inflate(layoutInflater) }

    override fun setupView() {
        Handler(Looper.getMainLooper()).postDelayed({
            moveNext()
        }, 2000L)
    }

    private fun moveNext() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

}