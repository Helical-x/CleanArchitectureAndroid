package com.example.ceibatest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ceibatest.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(user: List<UserEntity>)

    @Query("SELECT * from user")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * from user WHERE id= :id")
    suspend fun getUser(id: Int): List<UserEntity>
}