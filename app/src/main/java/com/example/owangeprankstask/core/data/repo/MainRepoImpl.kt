package com.example.owangeprankstask.core.data.repo

import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.MainLocalDataSrc
import com.example.owangeprankstask.core.data.remote.MainRemoteDataSrc

class MainRepoImpl(
    private val mainRemoteDataSrc: MainRemoteDataSrc,
    private val mainLocalDataSrc: MainLocalDataSrc
) : MainRepo {

    override fun addContacts(items: List<ContactItem>) =
        mainLocalDataSrc.insertContacts(items.toEntities())

    override fun deleteContacts() = mainLocalDataSrc.deleteContacts()

    override fun getContacts(): List<ContactItem> = mainLocalDataSrc.getContacts()

}