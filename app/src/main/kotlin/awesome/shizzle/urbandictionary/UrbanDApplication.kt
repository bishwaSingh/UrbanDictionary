package awesome.shizzle.urbandictionary

import android.app.Application
import awesome.shizzle.urbandictionary.di.appModule
import awesome.shizzle.urbandictionary.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UrbanDApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UrbanDApplication)
            androidLogger()
            modules(listOf(networkModule, appModule))
        }
    }

}