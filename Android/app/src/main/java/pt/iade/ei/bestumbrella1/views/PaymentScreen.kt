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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    var balance by remember { mutableStateOf(0.00) }
    var amountText by remember { mutableStateOf(TextFieldValue("")) }
    var showConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false, onClick = { navController.navigate("map") }, icon = { Icon(Icons.Default.Map, null) }, label = { Text("Mapa") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("qrscanner") }, icon = { Icon(Icons.Default.QrCodeScanner, null) }, label = { Text("Scanner") })
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
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Recarregar Saldo", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))

                Text("Saldo atual:", style = MaterialTheme.typography.bodyMedium)
                Text("€${"%.2f".format(balance)}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = amountText,
                    onValueChange = { amountText = it },
                    label = { Text("Valor a recarregar (€)") },
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                Button(onClick = {
                    val value = amountText.text.toDoubleOrNull()
                    if (value != null && value > 0) {
                        balance += value
                        amountText = TextFieldValue("")
                        showConfirmation = true
                    }
                }) {
                    Icon(Icons.Default.AttachMoney, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Recarregar")
                }

                Spacer(Modifier.height(24.dp))

                if (showConfirmation) {
                    Text("Recarregamento concluído!", color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}