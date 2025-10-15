package pt.iade.ei.bestumbrella1.models

data class User(
    val id: Int,
    val nome: String,
    val email: String,
    val password: String
)