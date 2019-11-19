package com.lucasmarciano.pwc.utils

import android.app.Application
import com.lucasmarciano.pwc.injection.viewsModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application class, to configure the dependency injection and the SharedPreference
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(viewsModules))
        }
    }
}