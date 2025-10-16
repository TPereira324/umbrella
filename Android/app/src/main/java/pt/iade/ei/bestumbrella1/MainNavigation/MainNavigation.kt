package pt.iade.ei.bestumbrella1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.bestumbrella1.data.UserRepository
import pt.ipleiria.estubetural.views.LoginScreen
import pt.ipleiria.estubetural.views.RegisterScreen

@Composable
fun MainNavigation(userRepository: UserRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                userRepository = userRepository,
                onLoginSuccess = {
                    // Aqui podes navegar para a tela principal, ex: "home"
                    // navController.navigate("home")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                userRepository = userRepository,
                onRegisterSuccess = {
                    // Após registo, volta para login ou navega para outra tela
                    navController.popBackStack("login", inclusive = false)
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // composable("home") { HomeScreen(...) }
        // composable("profile") { ProfileScreen(...) }
    }
}