package com.example.superweather.ui.di

import androidx.lifecycle.ViewModel
import com.example.superweather.di.ViewModelKey
import com.example.superweather.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
