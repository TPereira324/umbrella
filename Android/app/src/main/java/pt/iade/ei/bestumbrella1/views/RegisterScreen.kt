package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pt.iade.ei.bestumbrella1.R
import androidx.compose.runtime.livedata.observeAsState
import pt.iade.ei.bestumbrella1.di.AppModule
import pt.iade.ei.bestumbrella1.viewmodels.AuthViewModel



@Composable
fun RegisterScreen(
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val context = androidx.compose.ui.platform.LocalContext.current
    val authViewModel = remember { AppModule.provideAuthViewModel(context) }
    val registerResult by authViewModel.registerResult.observeAsState()
    val isLoading by authViewModel.isLoading.observeAsState(false)
    val errorState by authViewModel.error.observeAsState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976D2), Color.White)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { authViewModel.register(name, email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
            ) {
                Text(if (isLoading) "Registrando..." else "Registrar", color = Color.White)
            }

            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Já tem conta? Entrar", color = Color(0xFF1976D2))
            }

            registerResult?.let { result ->
                if (result.success) {
                    onRegisterSuccess()
                } else if (result.message?.isNotEmpty() == true) {
                    error = result.message
                }
            }

            val displayedError = errorState ?: error
            displayedError?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navController = rememberNavController(),
        onRegisterSuccess = {}
    )
}