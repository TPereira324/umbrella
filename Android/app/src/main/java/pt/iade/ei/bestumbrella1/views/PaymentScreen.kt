package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun PaymentScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var statusMessage by remember { mutableStateOf("") }
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
                        }
                    }
                }
            ) {
                Text(" Pagar com PayPal")
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (statusMessage.isNotEmpty()) {
                Text(statusMessage)
            } else {
                Text("Escolhe pagar com PayPal e segue o fluxo.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    val navController = rememberNavController()
    PaymentScreen(navController = navController)
}