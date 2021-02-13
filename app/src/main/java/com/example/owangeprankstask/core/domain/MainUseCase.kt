package com.example.owangeprankstask.core.domain

import androidx.lifecycle.LiveData
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

interface MainUseCase {
    suspend fun saveContacts(contacts: List<ContactItem>)
    fun getContacts(): LiveData<List<ContactEntity>>
}