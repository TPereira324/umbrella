package pt.iade.ei.bestumbrella1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.room.Room
import pt.iade.ei.bestumbrella1.data.AppDatabase
import pt.iade.ei.bestumbrella1.data.UserRepository
import pt.iade.ei.bestumbrella1.navigation.MainNavigation
import pt.iade.ei.bestumbrella1.ui.theme.BestUmbrella1Theme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "bestumbrella-db"
        ).build()

        val userRepository = UserRepository(database.userDao())

        setContent {
            BestUmbrella1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(userRepository = userRepository)
                }
            }
        }
    }
}