package com.chrisa.helloworld

import com.chrisa.helloworld.home.HomeModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        HomeModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }
}
