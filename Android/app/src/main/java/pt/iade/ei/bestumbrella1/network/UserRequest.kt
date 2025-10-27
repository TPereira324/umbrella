package pt.iade.ei.bestumbrella1.network

data class UserRequest(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null
)