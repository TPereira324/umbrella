package pt.iade.ei.bestumbrella1.models

import pt.iade.ei.bestumbrella1.data.RetrofitClient
import pt.iade.ei.bestumbrella1.data.UserRequest
import pt.iade.ei.bestumbrella1.data.UserResponse

class UserRepository {

    suspend fun register(name: String, email: String, password: String): UserResponse {
        val api = RetrofitClient.instance
        return api.registerUser(UserRequest(name = name, email = email, password = password))
    }

    suspend fun login(email: String, password: String): UserResponse {
        val api = RetrofitClient.instance
        return api.loginUser(UserRequest(email = email, password = password))
    }
}
