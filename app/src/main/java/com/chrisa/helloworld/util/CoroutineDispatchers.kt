package com.chrisa.helloworld.util

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface CoroutineDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

internal class CoroutineDispatchersImpl @Inject constructor() :
    CoroutineDispatchers {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class CoroutineDispatchersModule {
    @Binds
    internal abstract fun bindCoroutineDispatchers(syncServiceDataImpl: CoroutineDispatchersImpl): CoroutineDispatchers
}
