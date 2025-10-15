package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.ui.theme.black
import pt.iade.ei.bestumbrella1.ui.theme.blue
import pt.iade.ei.bestumbrella1.ui.theme.white

@Composable
fun RegisterScreen(
    onLoginClick: (() -> Unit)? = null,
    onRegisterSuccess: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(blue, white)))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 1.dp)
        )

        Text(
            text = "Criar Conta",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nome
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text("Nome", color = black, fontWeight = FontWeight.Bold) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = nameError
        )

        if (nameError) {
            Text(
                text = " Nome obrigatório.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email", color = black, fontWeight = FontWeight.Bold) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError
        )

        if (emailError) {
            Text(
                text = " Email inválido.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Palavra-passe
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordMismatch = false
            },
            label = { Text("Palavra-passe", color = black, fontWeight = FontWeight.Bold) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Mostrar palavra-passe"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Confirmar palavra-passe
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordMismatch = false
            },
            label = { Text("Confirmar palavra-passe", color = black, fontWeight = FontWeight.Bold) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                    Icon(
                        imageVector = if (showConfirmPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Mostrar confirmar palavra-passe"
                    )
                }
            },
            isError = passwordMismatch
        )

        if (passwordMismatch) {
            Text(
                text = " As palavras-passe não coincidem.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botão Registar
        Button(
            onClick = {
                when {
                    name.isBlank() -> nameError = true
                    !isEmailValid(email) -> emailError = true
                    password != confirmPassword -> passwordMismatch = true
                    else -> onRegisterSuccess?.invoke()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Registar", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Link para login
        TextButton(onClick = { onLoginClick?.invoke() }) {
            Text("Já tens conta? Inicia sessão", color = black, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}