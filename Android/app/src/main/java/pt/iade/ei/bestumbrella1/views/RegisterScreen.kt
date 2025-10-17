package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.models.UserRepository
import pt.iade.ei.bestumbrella1.utils.isValidEmail
import pt.iade.ei.bestumbrella1.utils.isValidPassword


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    userRepository: UserRepository = UserRepository()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        Text("Criar Conta", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email",fontWeight = FontWeight.Bold,color = Color.Black) },
            leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = null) },
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            if (name.isBlank()) {
                error = "Nome obrigatório"
                return@Button
            }
            if (!isValidEmail(email)) {
                error = "Email inválido"
                return@Button
            }
            if (!isValidPassword(password)) {
                error = "Senha deve ter pelo menos 6 caracteres"
                return@Button
            }

            val success = userRepository.register(email, password)
            if (success) {
                error = null
                navController.navigate("login")
            } else {
                error = "Email já está em uso"
            }
        }) {
            Text("Registrar")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate("login") }) {
            Text("Já tem conta? Entrar")
        }

        error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}