package com.example.owangeprankstask.core.data.local

import com.example.owangeprankstask.core.data.local.entities.ContactEntity
import io.reactivex.Observable

interface MainLocalDataSrc {
    fun getContacts(): Observable<List<ContactEntity>>
    fun insertContacts(contactEntities: List<ContactEntity>)
    fun deleteContacts()
}