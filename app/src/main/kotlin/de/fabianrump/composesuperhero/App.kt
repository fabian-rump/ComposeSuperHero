package de.fabianrump.composesuperhero

import android.app.Application
import de.fabianrump.composesuperhero.ui.di.mainModule
import de.fabianrump.database.di.databaseModule
import de.fabianrump.domain.di.domainModule
import de.fabianrump.navigation.di.navigationModule
import de.fabianrump.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                mainModule,
                navigationModule,
                networkModule,
                databaseModule,
                domainModule
            )
        }
        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(DebugTree())
    }
}