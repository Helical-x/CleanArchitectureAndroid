package com.example.ceibatest.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class UserListScreenTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun userListTest() {
        val links = composeTestRule.onAllNodesWithText("VER PUBLICACIONES")
        composeTestRule.onNodeWithText("Buscar usuario").assertExists()
        links.onFirst().performClick()
        composeTestRule.onNodeWithText("qui est esse").assertExists()
    }

    @Test
    fun searchViewTest() {
        val textField = composeTestRule.onNodeWithText("Buscar usuario")
        textField.performClick()
        textField.performTextInput("0")
        composeTestRule.onNodeWithText("VER PUBLICACIONES").assertDoesNotExist()
    }
}