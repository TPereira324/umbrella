package pt.iade.ei.bestumbrella1.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pt.iade.ei.bestumbrella1.data.Repository
import pt.iade.ei.bestumbrella1.models.SessionManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.test.platform.app.InstrumentationRegistry

@RunWith(AndroidJUnit4::class)
class ApiIntegrationTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var repository: Repository
    private lateinit var sessionManager: SessionManager

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sessionManager = SessionManager(appContext)
        repository = Repository(apiService, sessionManager)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testLoginUser() = runBlocking {
        // Prepare mock response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                {
                    "success": true,
                    "message": "Login successful",
                    "token": "fake-token-12345",
                    "name": "Test User",
                    "email": "test@example.com"
                }
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // Execute the API call
        val result = repository.loginUser("test@example.com", "password123")

        // Verify the request
        val request = mockWebServer.takeRequest()
        assertEquals("/users/login", request.path)

        // Verify the result
        assertTrue(result.isSuccess)
        val userResponse = result.getOrNull()
        assertEquals(true, userResponse?.success)
        assertEquals("Login successful", userResponse?.message)
    }

    @Test
    fun testGetWeatherForecast() = runBlocking {
        // Prepare mock response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                {
                    "current": {
                        "temperature": 25.0,
                        "humidity": 70,
                        "windSpeed": 10.0,
                        "condition": "Sunny",
                        "icon": "01d"
                    },
                    "forecast": [
                        {
                            "date": "2023-10-15",
                            "temperature": 24.0,
                            "condition": "Cloudy",
                            "icon": "02d",
                            "precipitation": 0.0
                        }
                    ]
                }
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // Execute the API call
        val result = repository.getWeatherForecast(38.7223, -9.1393)

        // Verify the request
        val request = mockWebServer.takeRequest()
        assertEquals("/weather/forecast?lat=38.7223&lon=-9.1393", request.path)

        // Verify the result
        assertTrue(result.isSuccess)
        val weatherResponse = result.getOrNull()
        assertEquals(25.0, weatherResponse?.current?.temperature)
        assertEquals("Sunny", weatherResponse?.current?.condition)
        assertEquals(1, weatherResponse?.forecast?.size)
    }
}