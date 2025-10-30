package pt.iade.ei.bestumbrella1.models

class UserRepository {

    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    fun register(name: String, email: String, password: String): Boolean {
        if (users.any { it.email == email }) return false
        users.add(User(name, email, password))
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