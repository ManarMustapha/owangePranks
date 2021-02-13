package com.example.owangeprankstask.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.owangeprankstask.core.data.local.dao.ContactDao
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao
}