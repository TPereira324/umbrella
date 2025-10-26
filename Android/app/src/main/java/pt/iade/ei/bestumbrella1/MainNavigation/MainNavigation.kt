package pt.iade.ei.bestumbrella1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.LoginScreen
import pt.iade.ei.bestumbrella1.views.RegisterScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    val userRepository = UserRepository()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Tela de Login
        composable("login") {
            LoginScreen(
                navController = navController,
                userRepository = userRepository,
                onLoginSuccess = {
                    navController.navigate("map") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Tela de Registro
        composable("register") {
            RegisterScreen(
                navController = navController,
                userRepository = userRepository,
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Tela principal do mapa (após login)
        composable("map") {
            MapScreen(navController = navController)
        }
    }
}

@Composable
fun UserRepository() {
    TODO("Not yet implemented")
}

@Composable
fun MapScreen(navController: NavHostController) {
    TODO("Not yet implemented")
}
