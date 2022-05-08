package com.example.ceibatest.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.ceibatest.domain.model.Post

@Entity(
    tableName = "post",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = CASCADE
    )]
)

data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "userId")
    val userId: Int,
    val title: String,
    val body: String
)

fun Post.toDatabase() = PostEntity(userId = userId, title = title, body = body)
