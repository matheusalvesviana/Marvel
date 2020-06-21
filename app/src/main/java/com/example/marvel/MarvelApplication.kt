package com.example.marvel

import android.app.Application
import com.example.marvel.di.InjectionImpl
import org.koin.core.context.loadKoinModules

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val injection = InjectionImpl()

        loadKoinModules(injection.module)
    }
}