package pt.iade.ei.bestumbrella1.network

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val token: String? = null,
    val isSuccessful: Boolean = true
)