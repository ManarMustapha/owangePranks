package com.example.owangeprankstask.core.domain

import androidx.lifecycle.LiveData
import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.entities.ContactEntity
import com.example.owangeprankstask.core.data.repo.MainRepo

class MainUseCaseImpl(private val mainRepo: MainRepo) : MainUseCase {

    override suspend fun saveContacts(contacts: List<ContactItem>) =
        mainRepo.addContacts(contacts)

    override fun getContacts(): LiveData<List<ContactEntity>> =
        mainRepo.getContacts()
}