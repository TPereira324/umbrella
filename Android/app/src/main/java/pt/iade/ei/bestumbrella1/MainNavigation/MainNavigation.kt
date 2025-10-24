package pt.iade.ei.bestumbrella1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "map") {

        composable("map") {
            MapScreen(navController)
        }

        composable("qrscanner") {
            QrScannerScreen(navController)
        }

        composable("weather") {
            WeatherScreen(navController)
        }

        composable("history") {
            HistoryScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }
    }
}

@Composable
fun MapScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}
