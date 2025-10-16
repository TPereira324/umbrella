package pt.iade.ei.bestumbrella1.data

import pt.iade.ei.bestumbrella1.models.User

class FakeUserRepository : UserRepository(
    object : UserDao {
        override suspend fun insert(user: User) {}
        override suspend fun getByEmail(email: String): User? = null
    }
)