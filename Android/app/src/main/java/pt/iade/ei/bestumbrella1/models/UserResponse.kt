package pt.iade.ei.bestumbrella1.models

data class UserResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null,
    val name: String? = null
)