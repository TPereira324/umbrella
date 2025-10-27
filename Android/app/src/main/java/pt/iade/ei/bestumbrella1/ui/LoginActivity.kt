package pt.iade.ei.bestumbrella1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import pt.iade.ei.bestumbrella1.MainActivity
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.di.AppModule

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    // Obtém o ViewModel através do módulo de injeção de dependência
    private val authViewModel by lazy { AppModule.provideAuthViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa as views
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        // Configura os observadores para os dados do ViewModel
        setupObservers()

        // Configura os listeners de clique
        setupClickListeners()
    }

    private fun setupObservers() {
        // Observa o resultado do login
        authViewModel.loginResult.observe(this, Observer { response ->
            if (response.success) {
                // Login bem-sucedido, navega para a tela principal
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Login falhou, mostra mensagem de erro
                Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
            }
        })

        // Observa o estado de carregamento
        authViewModel.isLoading.observe(this, Observer { isLoading ->
            // Atualiza a UI para mostrar ou esconder o indicador de carregamento
            loginButton.isEnabled = !isLoading
            registerButton.isEnabled = !isLoading
        })

        // Observa erros
        authViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(email, password)) {
                // Chama o método de login do ViewModel
                authViewModel.login(email, password)
            }
        }

        registerButton.setOnClickListener {
            // Navega para a tela de registro
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            emailEditText.error = "Email é obrigatório"
            isValid = false
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Senha é obrigatória"
            isValid = false
        }

        return isValid
    }
}