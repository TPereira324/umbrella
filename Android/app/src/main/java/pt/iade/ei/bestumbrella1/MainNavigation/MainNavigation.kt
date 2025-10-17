package pt.iade.ei.bestumbrella1.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.data.UserRepository


@Composable
fun MainNavigation(navController: NavHostController) {
    val userRepository = UserRepository()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                userRepository = userRepository,
                onLoginSuccess = { navController.navigate("map") },,
            )
        }
        composable("register") { RegisterScreen(navController) }
        composable("map") { MapScreenWithMarkers(navController) }
        composable("qrscanner") { QrScannerScreen(onCodeScanned = { code -> /* handle code */ }) }
        composable("history") { HistoryScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
    }
}