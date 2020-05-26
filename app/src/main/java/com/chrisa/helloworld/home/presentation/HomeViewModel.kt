package com.chrisa.helloworld.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisa.helloworld.home.data.HomeDataSource
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val homeDataSource: HomeDataSource,
    val dispatcher: CoroutineContext
) : ViewModel() {

    private val _greetingResource = MutableLiveData<GreetingResource>()
    val greetingResource: LiveData<GreetingResource>
        get() = _greetingResource

    fun loadGreeting() {
        viewModelScope.launch(dispatcher) {
            try {
                val stayDetail = homeDataSource.loadGreeting()
                _greetingResource.postValue(GreetingResource.Success(stayDetail))
            } catch (throwable: Throwable) {
                _greetingResource.postValue(GreetingResource.Error)
            }
        }
    }
}

sealed class GreetingResource {
    data class Success(val greeting: String) : GreetingResource()
    object Error : GreetingResource()
}
