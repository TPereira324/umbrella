package pt.iade.ei.bestumbrella1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "map" // Tela inicial
    ) {
        // 🌍 Tela principal do mapa
        composable("map") {
            MapScreenWithMarkers(navController)
        }

        // 📷 Scanner de QR Code
        composable("qrscanner") {
            QrScannerScreen(navController as (String) -> Unit)
        }

        // 📄 Detalhes do Aluguer (exemplo com argumento de QR code)
        composable("rental/{qrCode}") { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: "N/A"
            RentalDetailsScreen(navController, qrCode)
        }

        // 💳 Pagamento
        composable("payment/{qrCode}") { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: "N/A"
            PaymentScreen(navController, qrCode)
        }

        // 🕓 Histórico
        composable("history") {
            HistoryScreen(navController)
        }

        // 👤 Perfil
        composable("profile") {
            ProfileScreen(navController)
        }
    }
}
