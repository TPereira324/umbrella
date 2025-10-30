package pt.iade.ei.bestumbrella1.views

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController, qrCode: String) {
    var balance by remember { mutableStateOf(5.00) } // saldo inicial fictício
    var amountText by remember { mutableStateOf(TextFieldValue("")) }
    var showConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("map") },
                    icon = { Icon(Icons.Default.Map, null) },
                    label = { Text("Mapa", color = Color.Black, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("qrscanner") },
                    icon = { Icon(Icons.Default.QrCodeScanner, null) },
                    label = { Text("Scanner", color = Color.Black, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("weather") },
                    icon = { Icon(Icons.Default.Cloud, contentDescription = null) },
                    label = { Text("Tempo", color = Color.Black, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.History, null) },
                    label = { Text("Histórico", color = Color.Black, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Perfil", color = Color.Black, fontWeight = FontWeight.Bold) }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2196F3).copy(alpha = 0.8f),
                            Color(0xFFE3F2FD)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    "Pagamento via PayPal",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Saldo atual:", style = MaterialTheme.typography.bodyMedium, color = Color.Black, fontWeight = FontWeight.Bold)
                        Text(
                            "€${"%.2f".format(balance)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(24.dp))

                        OutlinedTextField(
                            value = amountText,
                            onValueChange = { amountText = it },
                            label = { Text("Valor a pagar (€)", color = Color.Black, fontWeight = FontWeight.Bold) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = {
                                val value = amountText.text.toDoubleOrNull()
                                if (value != null && value > 0) {
                                    balance -= value
                                    amountText = TextFieldValue("")
                                    showConfirmation = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF003087) // Azul PayPal
                            )
                        ) {
                            Icon(Icons.Default.Payment, contentDescription = null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Pagar com PayPal", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Spacer(Modifier.height(16.dp))

                        if (showConfirmation) {
                            Text(
                                "Pagamento efetuado com sucesso via PayPal!",
                                color = Color(0xFF4CAF50),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    val navController = rememberNavController()
    val qrCode = ""
    PaymentScreen(navController, qrCode)
}

