package com.ridianputra.simplegithubusersapp

import android.app.Application
import com.ridianputra.core.di.databaseModule
import com.ridianputra.core.di.networkModule
import com.ridianputra.core.di.repositoryModule
import com.ridianputra.simplegithubusersapp.di.useCaseModule
import com.ridianputra.simplegithubusersapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
