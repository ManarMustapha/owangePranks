package com.example.owangeprankstask.platform.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.domain.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : ViewModel() {

    init {
        observeContacts()
    }

    fun saveContacts(contacts: List<ContactItem>) {
        viewModelScope.launch {
            mainUseCase.saveContacts(contacts)
        }
    }

    fun observeContacts() = mainUseCase.getContacts()
}