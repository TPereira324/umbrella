package pt.iade.ei.bestumbrella1.views
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {


        }
        composable("register") { RegisterScreen(navController) }
        composable("map") { MapScreenWithMarkers(navController) }
        composable("qrscanner") { QrScannerScreen(onCodeScanned = { code -> /* handle code */ }) }
        composable("history") { HistoryScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
    }
}