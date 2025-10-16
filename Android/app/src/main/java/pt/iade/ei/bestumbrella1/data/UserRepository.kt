import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.bestumbrella1.MainActivity
import pt.iade.ei.bestumbrella1.views.LoginScreen
import pt.iade.ei.bestumbrella1.views.RegisterScreen

@Composable
fun MainNavigation(userRepository: MainActivity.UserRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                userRepository = userRepository,
                onLoginSuccess = { navController.navigate("map") },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                userRepository = userRepository,
                onRegisterSuccess = { navController.navigate("map") },
                onLoginClick = { navController.popBackStack() }
            )
        }


    }
}