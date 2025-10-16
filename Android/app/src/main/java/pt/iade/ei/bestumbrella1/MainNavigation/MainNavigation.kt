package pt.iade.ei.bestumbrella1.MainNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun MainNavigation(userRepository: Any) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // 🟢 Login
        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("map") },
            )
        }

        // 🟢 Registo
        composable("register") {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("map") },
            )
        }

        // 🗺️ Mapa
        composable("map") {
            MapScreen(navController)
        }

        // 📷 Scanner QR
        composable("qrscanner") {
            QrScannerScreen(
                onCodeScanned = { code ->
                    navController.navigate("rentalDetails/$code")
                }
            )
        }

        // 📄 Detalhes do Aluguer
        composable(
            "rentalDetails/{qrCode}",
            arguments = listOf(navArgument("qrCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            RentalDetailsScreen(navController, qrCode)
        }

        // 💳 Pagamento
        composable(
            "payment/{qrCode}",
            arguments = listOf(navArgument("qrCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            PaymentScreen(navController, qrCode)
        }

        // 👤 Perfil
        composable("profile") {
            ProfileScreen(onLogoutClick = {
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        // 🔍 Scanner alternativo
        composable("scanner") {
            ScannerScreen()
        }
    }
}