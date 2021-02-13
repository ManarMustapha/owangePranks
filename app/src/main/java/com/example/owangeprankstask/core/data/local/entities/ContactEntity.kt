package com.example.owangeprankstask.core.data.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Contact")
data class ContactEntity(
    @PrimaryKey val id: Int? = null,
    var name: String,
    var phone: String,
    var number: Int
) : Parcelable