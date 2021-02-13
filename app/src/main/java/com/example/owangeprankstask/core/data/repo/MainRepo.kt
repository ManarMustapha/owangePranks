package com.example.owangeprankstask.core.data.repo

import androidx.lifecycle.LiveData
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

interface MainRepo {
    suspend fun addContacts(items: List<ContactItem>)
    fun getContacts(): LiveData<List<ContactEntity>>
    fun deleteContacts()
}