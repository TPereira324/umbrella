package pt.iade.ei.bestumbrella1.views

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

data class Station(
    val name: String,
    val location: LatLng,
    val available: Int,
    val total: Int
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreenWithMarkers(navController: NavController) {
    // 📍 Pede permissão de localização ao utilizador
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    val lisboaCenter = LatLng(38.7682, -9.0985)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisboaCenter, 15f)
    }

    val stations = listOf(
        Station("Metro Moscavide", LatLng(38.7685, -9.0975), 7, 10),
        Station("Metro Oriente", LatLng(38.7692, -9.0943), 5, 8),
        Station("Parque das Nações", LatLng(38.7670, -9.1008), 6, 10),
        Station("IADE", LatLng(38.7635, -9.0938), 3, 6)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Best Umbrella") }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate("qrscanner") },
                containerColor = Color(0xFF2196F3),
                icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "Desbloquear") },
                text = { Text("Desbloquear QR") }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Map, null) }, label = { Text("Mapa") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("history") }, icon = { Icon(Icons.Default.History, null) }, label = { Text("Histórico") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("profile") }, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") })
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF2196F3).copy(alpha = 0.7f),
                            Color(0xFFE3F2FD)
                        )
                    )
                )
        ) {
            // 🗺️ Google Map com permissão de localização
            GoogleMapWithPermission(cameraPositionState, stations, locationPermissionState.status.isGranted)
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun GoogleMapWithPermission(
    cameraPositionState: CameraPositionState,
    stations: List<Station>,
    locationEnabled: Boolean
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = locationEnabled)
    ) {
        stations.forEach { station ->
            Marker(
                state = MarkerState(position = station.location),
                title = station.name,
                snippet = "${station.available}/${station.total} disponíveis ☂️"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapScreen() {
    val navController = rememberNavController()
    MapScreenWithMarkers(navController)
}
