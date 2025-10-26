package pt.iade.ei.bestumbrella1.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class UserRequest(
    val name: String? = null,
    val email: String,
    val password: String
)

data class UserResponse(
    val success: Boolean,
    val message: String
)


interface ApiService {
    @POST("users/register")
    suspend fun registerUser(@Body request: UserRequest): UserResponse

    @POST("users/login")
    suspend fun loginUser(@Body request: UserRequest): UserResponse
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
