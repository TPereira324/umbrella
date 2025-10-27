package pt.iade.ei.bestumbrella1.network

import pt.iade.ei.bestumbrella1.data.UserRequest
import pt.iade.ei.bestumbrella1.models.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("users/register")
    suspend fun registerUser(@Body request: pt.iade.ei.bestumbrella1.data.UserRequest): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(@Body request: pt.iade.ei.bestumbrella1.data.UserRequest): Response<UserResponse>
    
    @GET("weather/forecast")
    suspend fun getWeatherForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Response<WeatherResponse>
    
    @GET("users/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserProfileResponse>
    
    @PUT("users/profile")
    suspend fun updateUserProfile(@Header("Authorization") token: String, @Body request: UpdateProfileRequest): Response<UserResponse>
}

data class WeatherResponse(
    val current: CurrentWeather,
    val forecast: List<ForecastItem>
)

data class CurrentWeather(
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val condition: String,
    val icon: String
)

data class ForecastItem(
    val date: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val precipitation: Double
)

data class UserProfileResponse(
    val id: Long,
    val name: String,
    val email: String,
    val preferences: UserPreferences
)

data class UserPreferences(
    val notificationsEnabled: Boolean,
    val temperatureUnit: String,
    val defaultLocation: Location?
)

data class Location(
    val latitude: Double,
    val longitude: Double,
    val name: String
)

data class UpdateProfileRequest(
    val name: String? = null,
    val preferences: UserPreferences? = null
)
data class UserRequest(
    val name: String? = null,
    val email: String,
    val password: String
)
