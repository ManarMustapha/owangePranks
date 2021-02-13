package com.example.owangeprankstask

import android.app.Application
import com.example.owangeprankstask.koin.applicationModule
import com.example.owangeprankstask.koin.dbModule
import com.example.owangeprankstask.koin.mainModule
import org.koin.android.ext.android.startKoin

class ContactApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            applicationContext, modules = listOf(
                applicationModule,
                mainModule,
                dbModule
            )
        )
    }
}