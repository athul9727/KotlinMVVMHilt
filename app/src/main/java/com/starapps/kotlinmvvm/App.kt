package com.starapps.kotlinmvvm

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
//        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            modules(
//                apiModule,
//                viewModelModule,
//                databaseModule,
//                repositoryModule,
//                networkModule
//            )
//        }
    }
}