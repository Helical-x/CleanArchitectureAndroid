package com.example.ceibatest.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ceibatest.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)


fun User.toDatabase() = UserEntity(id = id, name = name, email = email, phone = phone)