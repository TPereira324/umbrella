package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    qrCode: String
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pagamento") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Método de Pagamento: PayPal")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Valor a pagar: 2,50 €", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    navController.navigate("map") {
                        popUpTo("map") { inclusive = true }
                    }
                }
            ) {
                Text(" Pagar com PayPal")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Pagamento simulado — integração PayPal virá depois.")
        }
    }
}
