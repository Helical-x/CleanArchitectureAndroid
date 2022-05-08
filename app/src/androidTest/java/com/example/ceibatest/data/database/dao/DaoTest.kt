package com.example.ceibatest.data.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.ceibatest.data.database.UserRoomDatabase
import com.example.ceibatest.data.database.entities.PostEntity
import com.example.ceibatest.data.database.entities.UserEntity
import com.example.ceibatest.data.database.entities.toDatabase
import com.example.ceibatest.domain.model.Post
import com.example.ceibatest.domain.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    @get:Rule
    var instanExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserRoomDatabase
    private lateinit var dao: UserDao
    private lateinit var dao2: PostDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()
        dao2 = database.postDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAllUsers() = runBlockingTest {
        val userList: List<UserEntity> =
            listOf(User(1, "Harol", "harol@mail.com", "5012354").toDatabase())
        dao.insertAll(userList)
        val allUsers = dao.getAllUsers()
        assert(allUsers.contains(userList[0]))
    }

    @Test
    fun insertAllPosts() = runBlockingTest {
        val userList: List<UserEntity> =
            listOf(User(1, "Harol", "harol@mail.com", "5012354").toDatabase())
        val postList: List<PostEntity> = listOf(Post(1, "titleSample", "bodySample").toDatabase())
        dao.insertAll(userList)
        dao2.insertAll(postList)
        val allPosts = dao2.getUserPosts(1)
        assert(allPosts.isNotEmpty())
    }
}