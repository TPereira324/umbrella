package pt.iade.ei.bestumbrella1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.iade.ei.bestumbrella1.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}