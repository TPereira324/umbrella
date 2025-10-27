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
import pt.iade.ei.bestumbrella1.network.CurrentWeather
import pt.iade.ei.bestumbrella1.network.ForecastItem
import pt.iade.ei.bestumbrella1.network.WeatherResponse

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherViewModel
    private val repository: Repository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = WeatherViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getWeatherForecast with valid coordinates updates weatherData`() = runTest {
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
        
        coEvery { repository.getWeatherForecast(latitude, longitude) } returns Result.success(weatherResponse)
        
        // Act
        viewModel.getWeatherForecast(latitude, longitude)
        
        // Assert
        assertEquals(weatherResponse, viewModel.weatherData.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `getWeatherForecast with error updates error state`() = runTest {
        // Arrange
        val latitude = 38.7223
        val longitude = -9.1393
        val errorMessage = "Failed to fetch weather data"
        
        coEvery { repository.getWeatherForecast(latitude, longitude) } returns Result.failure(Exception(errorMessage))
        
        // Act
        viewModel.getWeatherForecast(latitude, longitude)
        
        // Assert
        assertEquals(errorMessage, viewModel.error.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}