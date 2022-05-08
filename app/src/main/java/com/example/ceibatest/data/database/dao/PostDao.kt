package com.example.ceibatest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ceibatest.data.database.entities.PostEntity


@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(post: List<PostEntity>)

    @Query("SELECT * FROM post WHERE userId= :id")
    suspend fun getUserPosts(id: Int): List<PostEntity>
}