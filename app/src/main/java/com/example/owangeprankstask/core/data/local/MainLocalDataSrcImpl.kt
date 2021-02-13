package com.example.owangeprankstask.core.data.local

import com.example.owangeprankstask.core.data.local.entities.ContactEntity

class MainLocalDataSrcImpl(private val appDatabase: AppDatabase) : MainLocalDataSrc {

    override suspend fun insertContacts(contactEntities: List<ContactEntity>) =
        appDatabase.getContactDao().insertContacts(contactEntities)

    override fun deleteContacts() = appDatabase.getContactDao().deleteContacts()

    override fun getContacts() = appDatabase.getContactDao().getContacts()
}