package com.studioprojectx

import android.app.Application
import com.studioprojectx.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SPXApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@SPXApplication)
            modules(
                firebaseModule
            )
        }
    }
}