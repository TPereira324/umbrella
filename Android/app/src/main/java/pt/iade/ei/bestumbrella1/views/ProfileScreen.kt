package pt.iade.ei.bestumbrella1.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import pt.iade.ei.bestumbrella1.ui.theme.blue
import pt.iade.ei.bestumbrella1.ui.theme.white

data class Pagamento(
    val id: Int,
    val descricao: String,
    val valor: Double,
    val data: String
)

@SuppressLint("DefaultLocale")
@Composable
fun ProfileScreen(
    userName: String = "Taha-Wur Pereira",
    userEmail: String = "tahawur@email.com",
    onLogoutClick: (() -> Unit)? = null
) {
    val pagamentos = remember {
        listOf(
            Pagamento(1, "Aluguer Estação Moscavide", 2.50, "12/10/2025"),
            Pagamento(2, "Aluguer Estação Iade", 1.80, "09/10/2025"),
            Pagamento(3, "Aluguer Estação Oriente", 2.00, "05/10/2025")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(blue, white))
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Foto de Perfil",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = userName,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text(
            text = userEmail,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Aqui abres integração PayPal */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = Icons.Default.Payment,
                contentDescription = "PayPal",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Gerir método de pagamento (PayPal)")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Histórico de Pagamentos",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(pagamentos) { pagamento ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(pagamento.descricao, fontWeight = FontWeight.Bold)
                            Text("Data: ${pagamento.data}", style = MaterialTheme.typography.bodySmall)
                        }
                        Text(
                            text = "€${String.format("%.2f", pagamento.valor)}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { onLogoutClick?.invoke() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "Sair",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Terminar sessão", fontWeight = FontWeight.Bold)
        }
    }
}
