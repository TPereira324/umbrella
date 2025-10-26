package pt.iade.ei.bestumbrella1.models

data class UserRequest(
    val name: String? = null,
    val email: String,
    val password: String
)