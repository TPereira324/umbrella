package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalDetailsScreen(
    navController: NavController,
    qrCode: String
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalhes do Aluguer") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Código do Guarda-Chuva: $qrCode", style = MaterialTheme.typography.bodyLarge)
            Text("Localização: Moscavide Central", style = MaterialTheme.typography.bodyMedium)
            Text("Duração estimada: 2 horas", style = MaterialTheme.typography.bodyMedium)
            Text("Preço: 2,50 €", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("payment/$qrCode") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar e Pagar")
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RentalDetailsScreenPreview() {
    val navController = rememberNavController()
    RentalDetailsScreen(navController = navController, qrCode = "ABC123XYZ")
}