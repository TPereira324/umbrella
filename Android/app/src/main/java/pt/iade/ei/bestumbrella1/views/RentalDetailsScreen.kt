package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            TopAppBar(
                title = { Text("Detalhes do Aluguer", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD), // Branco azulado topo
                            Color(0xFF2196F3).copy(alpha = 0.7f) // Azul suave em baixo
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Resumo do Aluguer",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D47A1)
                    )
                )

                Divider(color = Color(0xFF90CAF9), thickness = 2.dp, modifier = Modifier.fillMaxWidth())

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Código do Guarda-Chuva:", fontWeight = FontWeight.Bold)
                        Text(qrCode, color = Color(0xFF1976D2), fontSize = 18.sp)

                        Spacer(Modifier.height(8.dp))

                        Text("Localização: Moscavide Central", style = MaterialTheme.typography.bodyMedium)
                        Text("Duração estimada: 2 horas", style = MaterialTheme.typography.bodyMedium)
                        Text("Preço: €2,50", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { navController.navigate("payment/$qrCode") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Confirmar e Pagar", color = Color.White)
                }

                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1976D2))
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRentalDetailsScreen() {
    val navController = rememberNavController()
    RentalDetailsScreen(navController = navController, qrCode = "ABC123XYZ")
}
