package com.example.owangeprankstask.core.data.repo

import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

interface MainRepo {
    fun addContacts(items: List<ContactItem>)
    fun getContacts(): List<ContactItem>
    fun deleteContacts()
}