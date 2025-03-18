package com.okproject.flowless

import android.app.Application
import com.okproject.flowless.data.di.coroutineModule
import com.okproject.flowless.data.di.dataSourceModule
import com.okproject.flowless.data.di.repositoryModule
import com.okproject.flowless.data.di.storageModule
import com.okproject.flowless.di.viewModelModule
import com.okproject.flowless.domain.di.brushModule
import com.okproject.flowless.domain.di.roleModule
import com.okproject.flowless.logging.LoggingTree
import com.okproject.flowless.logging.TimberLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class FlowlessApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(LoggingTree())
        startKoin {
            logger(TimberLogger())
            androidContext(this@FlowlessApplication)
            modules(
                viewModelModule,
                roleModule,
                brushModule,
                coroutineModule,
                storageModule,
                dataSourceModule,
                repositoryModule
            )
        }
    }
}