package pt.iade.ei.bestumbrella1.MainNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.bestumbrella1.views.*

@Composable
fun MainNavigation(
    navController: NavHostController
) {


        NavHost(navController, startDestination = "profile") {
            composable("profile") { ProfileScreen(navController) }
            composable("payment") { }
            composable("map") { /* TODO */ }
            composable("qrscanner") { /* TODO */ }
            composable("weather") { /* TODO */ }
            composable("history") { /* TODO */ }
        }
    }



@Composable
fun MapScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}
