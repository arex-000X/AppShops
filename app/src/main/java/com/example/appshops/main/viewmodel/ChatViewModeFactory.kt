package com.example.appshops.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatViewModeFactory(currentUserId: String, currentOthersUserId: String) :
    ViewModelProvider.Factory {

    val currentUserId: String
    val currentOthersUserId: String

    init {
        this.currentUserId = currentUserId
        this.currentOthersUserId =currentOthersUserId
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(currentUserId,currentOthersUserId) as T
    }
}