package pt.iade.ei.bestumbrella1.data

import androidx.room.*
import pt.iade.ei.bestumbrella1.models.User

@Dao
interface UserDao {
    @Insert suspend fun insert(user: User)
    @Query("SELECT * FROM User WHERE email = :email") suspend fun getByEmail(email: String): User?
}