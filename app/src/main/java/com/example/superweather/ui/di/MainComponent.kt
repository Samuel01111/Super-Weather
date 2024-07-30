package com.example.superweather.ui.di

import androidx.annotation.Keep
import com.example.superweather.MainActivity
import dagger.Subcomponent

@Keep
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Keep
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}
