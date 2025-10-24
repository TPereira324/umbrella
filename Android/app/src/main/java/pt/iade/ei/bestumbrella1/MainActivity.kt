package pt.iade.ei.bestumbrella1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Para usar toda a área do ecrã

        setContent {
            BestUmbrella1App()
        }
    }
}

@Composable
fun BestUmbrella1App() {
    BestUmbrella1Theme {
        // Fundo do app
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            val navController = rememberNavController()
            MainNavigation(navController = navController)
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun BestUmbrella1Theme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}