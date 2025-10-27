package pt.iade.ei.bestumbrella1.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pt.iade.ei.bestumbrella1.data.Repository
import pt.iade.ei.bestumbrella1.models.UserResponse
import pt.iade.ei.bestumbrella1.network.UserProfileResponse
import pt.iade.ei.bestumbrella1.network.UserPreferences
import pt.iade.ei.bestumbrella1.network.Location

@ExperimentalCoroutinesApi
class AuthViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AuthViewModel
    private val repository: Repository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = AuthViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login with valid credentials updates loginResult`() = runTest {
        // Arrange
        val email = "test@example.com"
        val password = "password123"
        val userResponse = UserResponse(success = true, message = "Login successful")
        
        coEvery { repository.loginUser(email, password) } returns Result.success(userResponse)
        
        // Act
        viewModel.login(email, password)
        
        // Assert
        assertEquals(userResponse, viewModel.loginResult.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `login with invalid credentials updates error`() = runTest {
        // Arrange
        val email = "test@example.com"
        val password = "wrong_password"
        val errorMessage = "Invalid credentials"
        
        coEvery { repository.loginUser(email, password) } returns Result.failure(Exception(errorMessage))
        
        // Act
        viewModel.login(email, password)
        
        // Assert
        assertEquals(errorMessage, viewModel.error.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `register with valid data updates registerResult`() = runTest {
        // Arrange
        val name = "Test User"
        val email = "test@example.com"
        val password = "password123"
        val userResponse = UserResponse(success = true, message = "Registration successful")
        
        coEvery { repository.registerUser(name, email, password) } returns Result.success(userResponse)
        
        // Act
        viewModel.register(name, email, password)
        
        // Assert
        assertEquals(userResponse, viewModel.registerResult.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `getUserProfile with valid token updates userProfile`() = runTest {
        // Arrange
        val userProfileResponse = UserProfileResponse(
            id = 1,
            name = "Test User",
            email = "test@example.com",
            preferences = UserPreferences(
                notificationsEnabled = true,
                temperatureUnit = "C",
                defaultLocation = Location(
                    latitude = 38.7223,
                    longitude = -9.1393,
                    name = "Lisboa"
                )
            )
        )
        
        coEvery { repository.getUserProfile() } returns Result.success(userProfileResponse)
        
        // Act
        viewModel.getUserProfile()
        
        // Assert
        assertEquals(userProfileResponse, viewModel.userProfile.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}