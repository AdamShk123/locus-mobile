package com.example.locusmobile

import com.example.locusmobile.data.model.User
import com.example.locusmobile.fake.FakeUsersRepository
import com.example.locusmobile.ui.UserViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserViewModelTest {
    private val userViewModel = UserViewModel(FakeUsersRepository())

    @Test
    fun userViewModel_signInWithIncorrectID_notSignedIn() = runTest {
        userViewModel.signIn("null","password")
        assert(!userViewModel.isLoggedIn)
    }

    @Test
    fun userViewModel_signInWithCorrectID_signedIn() = runTest {
        userViewModel.signIn("1","password")
        assert(userViewModel.isLoggedIn)
        assertEquals(userViewModel.user, User("1","Adam","Shkolnik"))
    }
}