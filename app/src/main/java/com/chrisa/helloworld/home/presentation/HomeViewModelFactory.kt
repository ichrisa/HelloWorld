package com.chrisa.helloworld.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chrisa.helloworld.home.data.HomeDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val homeDataSource: HomeDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            homeDataSource = homeDataSource,
            dispatcher = Dispatchers.IO
        ) as T
    }

}