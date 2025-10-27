package pt.iade.ei.bestumbrella1.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pt.iade.ei.bestumbrella1.models.SessionManager
import pt.iade.ei.bestumbrella1.models.UserResponse
import pt.iade.ei.bestumbrella1.network.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryTest {

    private lateinit var repository: Repository
    private val apiService: ApiService = mockk()
    private val sessionManager: SessionManager = mockk()

    @Before
    fun setup() {
        repository = Repository(apiService, sessionManager)
    }

    @Test
    fun `loginUser with valid credentials returns success`() = runTest {
        // Arrange
        val email = "test@example.com"
        val password = "password123"
        val userRequest = UserRequest(email = email, password = password)
        val userResponse = UserResponse(success = true, message = "Login successful")
        
        coEvery { apiService.loginUser(any()) } returns Response.success(userResponse)
        
        // Act
        val result = repository.loginUser(email, password)
        
        // Assert
        assertTrue(result.isSuccess)
        assertEquals(userResponse, result.getOrNull())
    }

    @Test
    fun `registerUser with valid data returns success`() = runTest {
        // Arrange
        val name = "Test User"
        val email = "test@example.com"
        val password = "password123"
        val userResponse = UserResponse(success = true, message = "Registration successful")
        
        coEvery { apiService.registerUser(any()) } returns Response.success(userResponse)
        
        // Act
        val result = repository.registerUser(name, email, password)
        
        // Assert
        assertTrue(result.isSuccess)
        assertEquals(userResponse, result.getOrNull())
    }

    @Test
    fun `getWeatherForecast returns weather data`() = runTest {
        // Arrange
        val latitude = 38.7223
        val longitude = -9.1393
        val currentWeather = CurrentWeather(
            temperature = 25.0,
            humidity = 70,
            windSpeed = 10.0,
            condition = "Sunny",
            icon = "01d"
        )
        val forecastItem = ForecastItem(
            date = "2023-10-15",
            temperature = 24.0,
            condition = "Cloudy",
            icon = "02d",
            precipitation = 0.0
        )
        val weatherResponse = WeatherResponse(
            current = currentWeather,
            forecast = listOf(forecastItem)
        )
        
        coEvery { apiService.getWeatherForecast(latitude, longitude) } returns Response.success(weatherResponse)
        
        // Act
        val result = repository.getWeatherForecast(latitude, longitude)
        
        // Assert
        assertTrue(result.isSuccess)
        assertEquals(weatherResponse, result.getOrNull())
    }

    @Test
    fun `getUserProfile with valid token returns user profile`() = runTest {
        // Arrange
        val token = "valid_token"
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
        
        coEvery { sessionManager.getAuthToken() } returns token
        coEvery { apiService.getUserProfile("Bearer $token") } returns Response.success(userProfileResponse)
        
        // Act
        val result = repository.getUserProfile()
        
        // Assert
        assertTrue(result.isSuccess)
        assertEquals(userProfileResponse, result.getOrNull())
    }
}