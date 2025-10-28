package pt.iade.ei.bestumbrella1.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

// Interface da API: usa os modelos já definidos em pt.iade.ei.bestumbrella1.network
interface ApiService {
    @POST("users/register")
    suspend fun registerUser(@Body request: UserRequest): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(@Body request: UserRequest): Response<UserResponse>

    @GET("weather/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response<WeatherResponse>

    @GET("users/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserProfileResponse>

    @PUT("users/profile")
    suspend fun updateUserProfile(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Response<UserResponse>
}
