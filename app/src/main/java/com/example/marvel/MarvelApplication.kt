package com.example.marvel

import android.app.Application
import com.example.marvel.di.InjectionImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            printLogger(Level.DEBUG)
        }
        val injection = InjectionImpl()

        loadKoinModules(injection.module)
    }
}