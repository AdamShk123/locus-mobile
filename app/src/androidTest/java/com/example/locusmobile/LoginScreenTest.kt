package com.example.locusmobile

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocusable
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginScreenStarted_instantiatedCorrectly() {
        // Perform Actions

        // Check Results
        val usernameString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        val passwordString = composeTestRule.activity.getString(R.string.login_screen_password_field)
        composeTestRule.onNode(hasText(usernameString)).assertExists()
        composeTestRule.onNode(hasText(passwordString)).assertExists()
    }

    @Test
    fun inputEnteredIntoBothFields_loginButtonEnabled() {
        // Perform Actions
        val usernameString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        val passwordString = composeTestRule.activity.getString(R.string.login_screen_password_field)
        composeTestRule.onNode(hasText(usernameString)).performTextInput("username")
        composeTestRule.onNode(hasText(passwordString)).performTextInput("password")
        composeTestRule.onNode(hasText(passwordString)).performImeAction()

        // Check Results
        val loginString = composeTestRule.activity.getString(R.string.login_screen_login_button)
        val loginButton = composeTestRule.onNode(hasText(loginString) and hasClickAction())
        loginButton.assertExists()
        loginButton.assertIsEnabled()
    }

    fun hasClickLabel(label: String) = SemanticsMatcher("Clickable action with label: $label") {
        it.config.getOrNull(
            SemanticsActions.OnClick
        )?.label == label
    }

    @Test
    fun tappedOutsideWhileTextFieldFocused_clearedTextFieldFocus() {
        val usernameFieldString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        composeTestRule.onNode(hasText(usernameFieldString)).performClick()

        composeTestRule.onNode(hasText(usernameFieldString)).assertIsFocused()

        val composableString = composeTestRule.activity.getString(R.string.login_screen_composable)
        composeTestRule.onNode(hasClickAction() and hasClickLabel(composableString)).performClick()

        composeTestRule.onNode(hasText(usernameFieldString)).assertIsNotFocused()
    }
}