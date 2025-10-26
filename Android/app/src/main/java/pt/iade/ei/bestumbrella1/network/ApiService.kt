package pt.iade.ei.bestumbrella1.network

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
