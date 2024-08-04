package com.example.superweather.ui.di

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import com.example.superweather.di.ViewModelKey
import com.example.superweather.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Keep
@Module
interface MainModule {

    @Keep
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
