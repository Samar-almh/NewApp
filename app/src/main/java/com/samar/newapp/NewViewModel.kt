package com.samar.newapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NewViewModel : ViewModel() {
    val newItemLiveData: LiveData<List<NewItem>>
    init {
        newItemLiveData = NewApp().fetchNews()
    }
}