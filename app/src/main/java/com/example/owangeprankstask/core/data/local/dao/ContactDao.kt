package com.example.owangeprankstask.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.owangeprankstask.core.data.local.entities.ContactEntity
import io.reactivex.Observable

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contactEntities: List<ContactEntity>)

    @Query("SELECT * FROM Contact")
    fun getContacts(): Observable<List<ContactEntity>>

    @Query("DELETE FROM Contact")
    fun deleteContacts()
}