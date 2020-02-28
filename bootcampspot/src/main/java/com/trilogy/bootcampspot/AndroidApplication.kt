package com.trilogy.bootcampspot

import androidx.multidex.MultiDexApplication
import com.trilogy.bootcampspot.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(viewModelModule, domainModule, dataModule))
        }
    }

}