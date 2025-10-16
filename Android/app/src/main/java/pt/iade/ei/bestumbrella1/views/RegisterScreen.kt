package pt.iade.ei.bestumbrella1.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.data.FakeUserRepository
import pt.iade.ei.bestumbrella1.data.UserRepository
import pt.iade.ei.bestumbrella1.models.User

@Composable
fun RegisterScreen(
    userRepository: UserRepository,
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color.White)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(250.dp).padding(bottom = 16.dp)
            )

            Text("Criar Conta", style = MaterialTheme.typography.titleLarge,
                color = Color.Black,fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = false
                },
                label = { Text("Nome", fontWeight = FontWeight.Bold,color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                isError = nameError
            )
            if (nameError) {
                Text("Nome obrigatório", color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email", fontWeight = FontWeight.Bold,color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError
            )
            if (emailError) {
                Text("Email inválido", color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordMismatch = false
                },
                label = { Text("Palavra-passe", fontWeight = FontWeight.Bold, color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Mostrar palavra-passe")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    passwordMismatch = false
                },
                label = { Text("Confirmar palavra-passe", fontWeight = FontWeight.Bold,color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Mostrar confirmação")
                    }
                },
                isError = passwordMismatch
            )
            if (passwordMismatch) {
                Text("As palavras-passe não coincidem", color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    nameError = name.isBlank()
                    emailError = !isEmailValid(email)
                    passwordMismatch = password != confirmPassword

                    if (!nameError && !emailError && !passwordMismatch) {
                        scope.launch {
                            userRepository.registar(User(nome = name, email = email, password = password))
                            Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                            onRegisterSuccess()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onLoginClick) {
                Text("Já tens conta? Inicia sessão")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        userRepository = FakeUserRepository(),
        onLoginClick = {},
        onRegisterSuccess = {}
    )
}