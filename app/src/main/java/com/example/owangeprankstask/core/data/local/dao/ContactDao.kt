package com.example.owangeprankstask.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contactEntities: List<ContactEntity>)

    @Query("SELECT * FROM Contact")
    fun getContacts(): LiveData<List<ContactEntity>>

    @Query("DELETE FROM Contact")
    fun deleteContacts()
}