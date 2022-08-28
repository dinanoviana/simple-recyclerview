package com.whitestudio.sidejo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whitestudio.simplerecyclerview.ui.MainViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(Dispatchers.IO) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}