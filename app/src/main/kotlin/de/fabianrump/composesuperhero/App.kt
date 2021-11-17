package de.fabianrump.composesuperhero

import android.app.Application
import de.fabianrump.composesuperhero.ui.di.mainModule
import de.fabianrump.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                mainModule,
                navigationModule
            )
        }
    }
}