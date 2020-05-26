package com.chrisa.helloworld.home

import com.chrisa.helloworld.home.data.DataModule
import com.chrisa.helloworld.home.presentation.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        DataModule::class
    ]
)
abstract class HomeModule {
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment
}
