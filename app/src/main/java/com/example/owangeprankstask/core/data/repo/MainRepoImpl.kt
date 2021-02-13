package com.example.owangeprankstask.core.data.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.MainLocalDataSrc
import com.example.owangeprankstask.core.data.local.entities.ContactEntity
import com.example.owangeprankstask.core.data.remote.MainRemoteDataSrc
import com.example.owangeprankstask.util.toEntities

class MainRepoImpl(
    private val mainRemoteDataSrc: MainRemoteDataSrc,
    private val mainLocalDataSrc: MainLocalDataSrc
) : MainRepo {

    @WorkerThread
    override suspend fun addContacts(items: List<ContactItem>) =
        mainLocalDataSrc.insertContacts(items.toEntities())

    override fun deleteContacts() = mainLocalDataSrc.deleteContacts()

    override fun getContacts(): LiveData<List<ContactEntity>> = mainLocalDataSrc.getContacts()

}