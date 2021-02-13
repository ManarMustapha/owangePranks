package com.example.owangeprankstask.core.data.local

import androidx.lifecycle.LiveData
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

interface MainLocalDataSrc {
    suspend fun insertContacts(contactEntities: List<ContactEntity>)
    fun getContacts(): LiveData<List<ContactEntity>>
    fun deleteContacts()
}