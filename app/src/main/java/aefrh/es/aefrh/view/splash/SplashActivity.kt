package aefrh.es.aefrh.view.splash

import aefrh.es.aefrh.view.main.MainActivity
import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ActivitySplashBinding
import aefrh.es.aefrh.view.base.BaseActivity
import android.content.Intent
import android.os.Handler
import androidx.databinding.ViewDataBinding

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding?) {
        this.binding = binding as ActivitySplashBinding

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