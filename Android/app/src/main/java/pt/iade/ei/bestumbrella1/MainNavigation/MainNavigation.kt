package pt.iade.ei.bestumbrella1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "map" // Ecrã inicial da app
    ) {
        // Ecrã do mapa com as estações
        composable("map") {
            MapScreen(navController)
        }

        // Ecrã do scanner de QR Code
        composable("qrscanner") {
            QrScannerScreen(
                onCodeScanned = { code ->
                    navController.navigate("rentalDetails/$code")
                }
            )
        }

        // Ecrã de histórico de alugueres
        composable("history") {
            HistoryScreen(navController)
        }

        // Ecrã de perfil do utilizador
        composable("profile") {
            ProfileScreen(navController)
        }

        // Ecrã de pagamento (saldo ou PayPal)
        composable("payment") {
            PaymentScreen(navController)
        }

        // Ecrã com detalhes do aluguer (aparece após ler um QR Code)
        composable("rentalDetails/{qrCode}") { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            RentalDetailsScreen(navController, qrCode)
        }
    }
}

@Composable
fun MapScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}
