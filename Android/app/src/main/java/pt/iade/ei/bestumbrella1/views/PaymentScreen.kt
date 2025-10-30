package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
=======
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
>>>>>>> ff9f8cf87e3db0aa3ce82e42ebf66ca8bcb32da7
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.app.Activity
import com.braintreepayments.api.BraintreeClient
import com.braintreepayments.api.PayPalClient
import com.braintreepayments.api.PayPalCheckoutRequest
import com.braintreepayments.api.PayPalPaymentIntent
import pt.iade.ei.bestumbrella1.data.ApiConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD
fun PaymentScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var statusMessage by remember { mutableStateOf("") }
=======
fun PaymentScreen(navController: NavController, qrCode: String) {
    var balance by remember { mutableStateOf(5.00) } // saldo inicial fictício
    var amountText by remember { mutableStateOf(TextFieldValue("")) }
    var showConfirmation by remember { mutableStateOf(false) }

>>>>>>> ff9f8cf87e3db0aa3ce82e42ebf66ca8bcb32da7
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("map") },
                    icon = { Icon(Icons.Default.Map, null) },
                    label = { Text("Mapa") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("qrscanner") },
                    icon = { Icon(Icons.Default.QrCodeScanner, null) },
                    label = { Text("Scanner") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.History, null) },
                    label = { Text("Histórico") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Perfil") }
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
                            Color(0xFF2196F3).copy(alpha = 0.8f), // Azul topo
                            Color(0xFFE3F2FD)                      // Branco azulado
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
                    color = Color.White
                )

                Spacer(Modifier.height(16.dp))

<<<<<<< HEAD
            Button(
                onClick = {
                    val activity = context as? Activity
                    if (activity == null) {
                        statusMessage = "Erro: contexto inválido para iniciar PayPal."
                        return@Button
                    }

                    // Autorização: substitui por tokenization key ou client token do teu backend
                    val authorization = ApiConfig.BRAINTREE_AUTHORIZATION
                    val braintreeClient = BraintreeClient(context, authorization)
                    val payPalClient = PayPalClient(braintreeClient)

                    val request = PayPalCheckoutRequest("2.50")
                    request.currencyCode = "EUR"
                    request.intent = PayPalPaymentIntent.CAPTURE

                    payPalClient.tokenizePayPalAccount(activity, request) { result, error ->
                        if (error != null) {
                            statusMessage = "Erro no pagamento: ${error.message}"
                        } else if (result != null) {
                            statusMessage = "Pagamento aprovado! Nonce: ${result.paymentMethodNonce?.string}" 
                            navController.navigate("map") {
                                popUpTo("map") { inclusive = true }
                            }
                        } else {
                            statusMessage = "Pagamento cancelado."
=======
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Saldo atual:", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "€${"%.2f".format(balance)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF1565C0)
                        )

                        Spacer(Modifier.height(24.dp))

                        OutlinedTextField(
                            value = amountText,
                            onValueChange = { amountText = it },
                            label = { Text("Valor a pagar (€)") },
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
                            Text("Pagar com PayPal", color = Color.White)
                        }

                        Spacer(Modifier.height(16.dp))

                        if (showConfirmation) {
                            Text(
                                "Pagamento efetuado com sucesso via PayPal!",
                                color = Color(0xFF4CAF50),
                                style = MaterialTheme.typography.bodyMedium
                            )
>>>>>>> ff9f8cf87e3db0aa3ce82e42ebf66ca8bcb32da7
                        }
                    }
                }
            }
<<<<<<< HEAD

            Spacer(modifier = Modifier.height(16.dp))
            if (statusMessage.isNotEmpty()) {
                Text(statusMessage)
            } else {
                Text("Escolhe pagar com PayPal e segue o fluxo.")
            }
=======
>>>>>>> ff9f8cf87e3db0aa3ce82e42ebf66ca8bcb32da7
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
