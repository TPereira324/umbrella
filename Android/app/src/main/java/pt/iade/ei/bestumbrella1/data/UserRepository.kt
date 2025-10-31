package pt.iade.ei.bestumbrella1.data

import pt.iade.ei.bestumbrella1.models.User

open class UserRepository(private val dao: UserDao) {
    suspend fun registar(user: User) = dao.insert(user)
    suspend fun autenticar(email: String): User? = dao.getByEmail(email)
}