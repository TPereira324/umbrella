package pt.iade.ei.bestumbrella1.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.data.UserRepository

@Composable
fun LoginScreen(
    userRepository: UserRepository,
    onLoginSuccess: (() -> Unit)? = null,
    onRegisterClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "Logo da aplicação",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp)
        )

        Text("Iniciar Sessão", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                loginError = false
            },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                loginError = false
            },
            label = { Text("Palavra-passe") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    val user = userRepository.autenticar(email)
                    loginError = user == null || user.password != password
                    if (!loginError) {
                        Toast.makeText(context, "Bem-vindo, ${user?.nome}!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess?.invoke()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        if (loginError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Email ou palavra-passe incorretos", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { onRegisterClick?.invoke() }) {
            Text("Ainda não tens conta? Regista-te")
        }
    }
}