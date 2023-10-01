package com.example.startwithjetpack.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startwithjetpack.domain.useCases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {
        fun saveFirstEnter(isDone:Boolean){
            if (isDone){
                viewModelScope.launch{
                    appEntryUseCases.write()
                }
            }
        }
}