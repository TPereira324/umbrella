package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false, onClick = { navController.navigate("map") }, icon = { Icon(Icons.Default.Map, null) }, label = { Text("Mapa") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("qrscanner") }, icon = { Icon(Icons.Default.QrCodeScanner, null) }, label = { Text("Scanner") })
                NavigationBarItem(selected = false, onClick = { navController.navigate("history") }, icon = { Icon(Icons.Default.History, null) }, label = { Text("Histórico") })
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") })
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Perfil", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))

                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(72.dp))
                Text("tahawurpereira1@gmail.com", style = MaterialTheme.typography.bodyMedium)
                AssistChip(
                    onClick = {},
                    label = { Text("Eco Warrior") },
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("0", fontSize = 20.sp)
                        Text("Usos", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("50", fontSize = 20.sp)
                        Text("Pontos", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("€0.28", fontSize = 20.sp)
                        Text("Poupado", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(Modifier.height(24.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Saldo", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("€0.00", color = MaterialTheme.colorScheme.error, fontSize = 20.sp)
                            Button(onClick = { /* ação de recarregar */ }) {
                                Text("Recarregar")
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("Recarregue para começar a usar guarda-chuvas", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(Modifier.height(24.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Atividade Recente", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(16.dp))
                        Icon(Icons.Default.Umbrella, contentDescription = null, modifier = Modifier.size(48.dp))
                        Spacer(Modifier.height(8.dp))
                        Text("Nenhuma atividade ainda", style = MaterialTheme.typography.bodyMedium)
                        Text("Sua primeira reserva aparecerá aqui", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}