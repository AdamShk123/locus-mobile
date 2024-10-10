package com.example.locusmobile

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.editableText
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
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
    fun loginScreen_screenStarted_instantiatedCorrectly() {
        // Perform Actions

        // Check Results
        val usernameString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        val passwordString = composeTestRule.activity.getString(R.string.login_screen_password_field)
        composeTestRule.onNode(hasText(usernameString)).assertExists()
        composeTestRule.onNode(hasText(passwordString)).assertExists()
    }

    @Test
    fun loginScreen_inputEnteredIntoBothFields_loginButtonEnabled() {
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
    fun loginScreen_tappedOutsideWhileTextFieldFocused_clearedTextFieldFocus() {
        val usernameFieldString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        composeTestRule.onNode(hasText(usernameFieldString)).performClick()

        composeTestRule.onNode(hasText(usernameFieldString)).assertIsFocused()

        val composableString = composeTestRule.activity.getString(R.string.login_screen_composable)
        composeTestRule.onNode(hasClickAction() and hasClickLabel(composableString)).performClick()

        composeTestRule.onNode(hasText(usernameFieldString)).assertIsNotFocused()
    }

    fun hasEditableText(text: String) = SemanticsMatcher("Text field with editable text: $text") {
        it.config.getOrNull(
            SemanticsProperties.EditableText
        )?.text == text
    }

    @Test
    fun loginScreen_pressedIconButtonOnUsernameTextField_deletedText() {
        val usernameFieldString = composeTestRule.activity.getString(R.string.login_screen_username_field)
        composeTestRule.onNode(hasText(usernameFieldString)).performTextInput("test")

        val deleteIconString = composeTestRule.activity.getString(R.string.login_screen_username_field_delete_icon_button)
        composeTestRule.onNode(hasClickAction() and hasContentDescription(deleteIconString)).performClick()

        composeTestRule.onNode(hasText(usernameFieldString)).assert(hasEditableText(""))
    }
}