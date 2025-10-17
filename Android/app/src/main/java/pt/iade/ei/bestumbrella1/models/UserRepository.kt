package pt.iade.ei.bestumbrella1.models

data class User(
    val email: String,
    val password: String
)

class UserRepository {

    // Simulação de base de dados em memória
    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    fun register(email: String, password: String): Boolean {
        if (users.any { it.email == email }) return false // já existe
        users.add(User(email, password))
        return true
    }

    fun login(email: String, password: String): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user != null) {
            loggedInUser = user
            true
        } else {
            false
        }
    }

    fun getLoggedInUser(): User? = loggedInUser

    fun logout() {
        loggedInUser = null
    }

    fun isLoggedIn(): Boolean = loggedInUser != null
}