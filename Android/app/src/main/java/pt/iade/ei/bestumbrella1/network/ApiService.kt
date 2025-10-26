package pt.iade.ei.bestumbrella1.network

import pt.iade.ei.bestumbrella1.data.UserRequest
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
    suspend fun registerUser(@Body request: pt.iade.ei.bestumbrella1.data.UserRequest): UserResponse

    @POST("users/login")
    suspend fun loginUser(@Body request: UserRequest): UserResponse
}