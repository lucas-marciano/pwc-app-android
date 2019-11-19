package com.lucasmarciano.pwc.utils

import android.app.Application
import com.lucasmarciano.pwc.injection.viewsModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

val prefs: Prefs by lazy {
    MainApplication.prefs!!
}

/**
 * Application class, to configure the dependency injection and the SharedPreference
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class MainApplication : Application() {

    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(viewsModules))
        }
    }
}