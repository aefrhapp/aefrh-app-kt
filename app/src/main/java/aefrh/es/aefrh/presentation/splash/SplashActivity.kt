package aefrh.es.aefrh.presentation.splash

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ActivitySplashBinding
import aefrh.es.aefrh.presentation.base.BaseActivity
import aefrh.es.aefrh.presentation.main.MainActivity
import android.content.Intent
import android.os.Handler
import androidx.databinding.ViewDataBinding

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    override val layout: Int = R.layout.activity_splash

    override fun initUI(binding: ViewDataBinding?) {
        this.binding = binding as ActivitySplashBinding
    }

    override fun onCreate() {

        Handler().postDelayed({
            // Start activity
            startActivity(Intent(this, MainActivity::class.java))
            // Animate the loading of new activity
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            // Close this activity
            finish()
        }, 2000)

    }

}