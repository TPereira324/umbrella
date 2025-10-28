package pt.iade.ei.bestumbrella1.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.iade.ei.bestumbrella1.models.SessionManager
import pt.iade.ei.bestumbrella1.network.ApiService
import pt.iade.ei.bestumbrella1.network.UpdateProfileRequest
import pt.iade.ei.bestumbrella1.network.UserPreferences
import pt.iade.ei.bestumbrella1.network.UserProfileResponse
import pt.iade.ei.bestumbrella1.network.UserRequest
import pt.iade.ei.bestumbrella1.network.UserResponse
import pt.iade.ei.bestumbrella1.network.WeatherResponse
import retrofit2.Response

class Repository(private val apiService: ApiService, private val sessionManager: SessionManager) {

    suspend fun registerUser(name: String, email: String, password: String): Result<UserResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // Suporte a administrador: se o email for admin@bestumbrella e senha admin123,
                // retorna sucesso localmente sem chamar a API.
                if (email.equals("admin@bestumbrella", ignoreCase = true)) {
                    if (password == "admin123") {
                        val adminResponse = UserResponse(
                            id = "admin",
                            name = name.ifBlank { "Administrador" },
                            email = email,
                            token = "local-admin-token",
                            isSuccessful = true
                        )
                        return@withContext Result.success(adminResponse)
                    } else {
                        return@withContext Result.failure(Exception("Senha do administrador inválida"))
                    }
                }
                val request = UserRequest(name = name, email = email, password = password)
                val response = apiService.registerUser(request)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Falha no registro: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loginUser(email: String, password: String): Result<UserResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // Suporte a administrador: autenticação local sem backend
                if (email.equals("admin@bestumbrella", ignoreCase = true)) {
                    if (password == "admin123") {
                        val adminResponse = UserResponse(
                            id = "admin",
                            name = "Administrador",
                            email = email,
                            token = "local-admin-token",
                            isSuccessful = true
                        )
                        return@withContext Result.success(adminResponse)
                    } else {
                        return@withContext Result.failure(Exception("Senha do administrador inválida"))
                    }
                }
                val request = UserRequest(email = email, password = password)
                val response = apiService.loginUser(request)
                if (response.isSuccessful) {
                    val userResponse = response.body()!!
                    // Aqui você pode salvar o token de autenticação se o back-end retornar
                    // sessionManager.saveAuthToken(userResponse.token)
                    Result.success(userResponse)
                } else {
                    Result.failure(Exception("Falha no login: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getWeatherForecast(latitude: Double, longitude: Double): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getWeatherForecast(latitude, longitude)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Falha ao obter previsão do tempo: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getUserProfile(): Result<UserProfileResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = sessionManager.getAuthToken()
                if (token.isNullOrEmpty()) {
                    return@withContext Result.failure(Exception("Usuário não autenticado"))
                }
                
                val response = apiService.getUserProfile("Bearer $token")
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Falha ao obter perfil: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun updateUserProfile(name: String?, preferences: UserPreferences?): Result<UserResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = sessionManager.getAuthToken()
                if (token.isNullOrEmpty()) {
                    return@withContext Result.failure(Exception("Usuário não autenticado"))
                }
                
                val request = UpdateProfileRequest(name, preferences)
                val response = apiService.updateUserProfile("Bearer $token", request)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Falha ao atualizar perfil: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}