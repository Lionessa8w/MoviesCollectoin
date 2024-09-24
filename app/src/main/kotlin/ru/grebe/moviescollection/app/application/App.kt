package ru.grebe.moviescollection.app.application

import android.app.Application
import ru.grebe.moviescollection.app.modules.appModule
import ru.grebe.moviescollection.app.modules.dataModule
import ru.grebe.moviescollection.app.modules.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}