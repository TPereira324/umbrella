package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class RentalEntry(
    val date: String,
    val from: String,
    val to: String,
    val cost: String,
    val duration: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    val entries = listOf(
        RentalEntry("Hoje, 14:30", "Metro Moscavide", "Parque das Nações", "€0.29", "35 min"),
        RentalEntry("Ontem, 09:16", "Vasco da Gama Shopping", "Metro Oriente", "€1.00", "1h 15min"),
        RentalEntry("Ontem, 08:03", "Vasco da Gama Shopping", "Vasco da Gama Shopping", "€0.23", "55 min")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false, onClick = { navController.navigate("map") }, icon = { Icon(Icons.Default.Map, null) }, label = { Text("Mapa") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("qrscanner") }, icon = { Icon(Icons.Default.QrCodeScanner, null) }, label = { Text("Scanner") })
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.History, null) }, label = { Text("Histórico") })
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
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Histórico", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("7", style = MaterialTheme.typography.titleLarge)
                            Text("Usos", style = MaterialTheme.typography.bodySmall)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("5h 45min", style = MaterialTheme.typography.titleLarge)
                            Text("Tempo Total", style = MaterialTheme.typography.bodySmall)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("€2.88", style = MaterialTheme.typography.titleLarge)
                            Text("Gasto Total", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                LazyColumn {
                    items(entries) { entry ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("${entry.date} — Concluído", style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.LocationOn, tint = MaterialTheme.colorScheme.primary, contentDescription = null)
                                    Spacer(Modifier.width(4.dp))
                                    Text("De: ${entry.from}", style = MaterialTheme.typography.bodyMedium)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.LocationOn, tint = MaterialTheme.colorScheme.error, contentDescription = null)
                                    Spacer(Modifier.width(4.dp))
                                    Text("Para: ${entry.to}", style = MaterialTheme.typography.bodyMedium)
                                }
                                Spacer(Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                    Text("Duração: ${entry.duration}")
                                    Text("Custo: ${entry.cost}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}