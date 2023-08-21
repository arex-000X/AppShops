package com.example.appshops

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GlobalViewModelFactory(private val fragmentManager: FragmentManager): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GlobalViewModel(fragmentManager) as T
    }
}