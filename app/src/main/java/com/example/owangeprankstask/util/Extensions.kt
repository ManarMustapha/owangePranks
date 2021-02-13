package com.example.owangeprankstask.util

import com.example.owangeprankstask.core.data.ContactItem
import com.example.owangeprankstask.core.data.local.entities.ContactEntity

fun List<ContactItem>.toEntities(): List<ContactEntity> {
    val entities = ArrayList<ContactEntity>()
    this.forEach { entities.add(ContactEntity(it.id, it.name, it.phone, it.number)) }
    return entities
}

fun List<ContactEntity>.toModels(): List<ContactItem> {
    val entities = ArrayList<ContactItem>()
    this.forEach { entities.add(ContactItem(it.id ?: "", it.name, it.phone, it.number)) }
    return entities
}