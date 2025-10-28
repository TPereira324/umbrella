package pt.iade.ei.bestumbrella1.MainNavigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import pt.iade.ei.bestumbrella1.views.LoginScreen
import pt.iade.ei.bestumbrella1.views.RegisterScreen

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

        composable("map") {
            MapScreen(navController = navController)
        }

        composable("cameraPreview") {
            CameraPreviewScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MapScreen(navController: NavHostController) {
    Scaffold {
        Text(text = "Mapa (em construção)")
    }
}