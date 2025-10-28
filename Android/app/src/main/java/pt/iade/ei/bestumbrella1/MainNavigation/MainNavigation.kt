package pt.iade.ei.bestumbrella1.MainNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.LoginScreen
import pt.iade.ei.bestumbrella1.views.RegisterScreen
import pt.iade.ei.bestumbrella1.views.MapScreenWithMarkers
import pt.iade.ei.bestumbrella1.views.QrScannerScreen
import pt.iade.ei.bestumbrella1.views.WeatherScreen
import pt.iade.ei.bestumbrella1.views.HistoryScreen
import pt.iade.ei.bestumbrella1.views.ProfileScreen
import pt.iade.ei.bestumbrella1.views.CameraPreviewScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate("map") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                navController = navController,
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("map") { MapScreenWithMarkers(navController) }
        composable("qrscanner") { QrScannerScreen(navController) }
        composable("weather") { WeatherScreen(navController) }
        composable("history") { HistoryScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("cameraPreview") { CameraPreviewScreen() }
    }
}