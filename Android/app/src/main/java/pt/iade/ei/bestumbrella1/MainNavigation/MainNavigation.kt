package pt.iade.ei.bestumbrella1.MainNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // 🟢 Tela de Login
        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("map") }
            )
        }

        // 🟢 Tela de Registo
        composable("register") {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() },
                onRegisterSuccess = { navController.navigate("map") }
            )
        }

        // 🗺️ Tela do Mapa
        composable("map") {
            MapScreen(navController)
        }

        // 📷 Tela de Leitura de QR Code
        composable("qrscanner") {
            QrScannerScreen(
                onCodeScanned = { code ->
                    navController.navigate("rentalDetails/$code")
                }
            )
        }

        // 📄 Tela de Detalhes do Aluguer
        composable(
            "rentalDetails/{qrCode}",
            arguments = listOf(navArgument("qrCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            RentalDetailsScreen(navController, qrCode)
        }

        // 💳 Tela de Pagamento (PayPal)
        composable(
            "payment/{qrCode}",
            arguments = listOf(navArgument("qrCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            PaymentScreen(navController, qrCode)
        }
    }
}
