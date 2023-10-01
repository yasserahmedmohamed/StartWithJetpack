package com.example.startwithjetpack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwithjetpack.domain.useCases.appEntry.AppEntryUseCases
import com.example.startwithjetpack.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases):ViewModel() {
    var keepSplashAppear by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<Route>(Route.AppStartNavigation)
        private set

    init {
        appEntryUseCases.read().onEach{
            if (it){
                startDestination = Route.NewsNavigation
            }else{
                startDestination = Route.AppStartNavigation
            }
            delay(300)
            keepSplashAppear = false
        }.launchIn(viewModelScope)
    }
}