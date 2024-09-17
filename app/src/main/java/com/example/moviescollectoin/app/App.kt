package com.example.moviescollectoin.app

import android.app.Application
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

        }
    }
}