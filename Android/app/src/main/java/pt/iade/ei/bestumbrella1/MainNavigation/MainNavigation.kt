package pt.iade.ei.bestumbrella1.navigation

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

    NavHost(navController = navController, startDestination = "map") {
        composable("map") {
            MapScreen(navController)
        }
        composable("payment/{qrCode}", arguments = listOf(
            navArgument("qrCode") { type = NavType.StringType }
        )) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            PaymentScreen(navController)
        }
        composable("profile") {
            ProfileScreen(onLogoutClick = { navController.navigate("map") })
        }
        composable("qrscanner") {
            QrScannerScreen(onCodeScanned = { code ->
                navController.navigate("rental/$code")
            })
        }
        composable("rental/{qrCode}", arguments = listOf(
            navArgument("qrCode") { type = NavType.StringType }
        )) { backStackEntry ->
            val qrCode = backStackEntry.arguments?.getString("qrCode") ?: ""
            RentalDetailsScreen(navController, qrCode)
        }
        composable("scanner") {
            ScannerScreen()
        }
    }
}