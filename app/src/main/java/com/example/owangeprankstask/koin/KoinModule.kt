package com.example.owangeprankstask.koin

import androidx.room.Room
import com.example.owangeprankstask.core.data.local.AppDatabase
import com.example.owangeprankstask.core.data.local.MainLocalDataSrc
import com.example.owangeprankstask.core.data.local.MainLocalDataSrcImpl
import com.example.owangeprankstask.core.data.remote.MainRemoteDataSrc
import com.example.owangeprankstask.core.data.remote.MainRemoteDataSrcImpl
import com.example.owangeprankstask.core.data.repo.MainRepo
import com.example.owangeprankstask.core.data.repo.MainRepoImpl
import com.example.owangeprankstask.core.domain.MainUseCase
import com.example.owangeprankstask.core.domain.MainUseCaseImpl
import com.example.owangeprankstask.platform.mainscreen.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single<MainRemoteDataSrc> { MainRemoteDataSrcImpl() }
    single<MainRepo> { MainRepoImpl(get(), get()) }
    single<MainUseCase> { MainUseCaseImpl(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "Contact"
        ).build()
    }
    single<MainLocalDataSrc> { MainLocalDataSrcImpl(get()) }
}