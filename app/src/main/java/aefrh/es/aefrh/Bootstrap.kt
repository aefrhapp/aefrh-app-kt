package aefrh.es.aefrh

import aefrh.es.aefrh.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Bootstrap : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Adding Koin modules to our application
        startKoin {
            androidLogger()
            androidContext(this@Bootstrap)
            modules(listOf(viewModelModule))
        }

    }

}