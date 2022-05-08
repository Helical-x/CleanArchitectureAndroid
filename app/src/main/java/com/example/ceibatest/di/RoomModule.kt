package com.example.ceibatest.di

import android.content.Context
import androidx.room.Room
import com.example.ceibatest.data.database.UserRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private val DATABASE_NAME = "user_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserRoomDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideUserDao(db: UserRoomDatabase) = db.userDao()

    @Provides
    @Singleton
    fun providePostDao(db: UserRoomDatabase) = db.postDao()

}