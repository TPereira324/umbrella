package pt.iade.ei.bestumbrella1.network

data class UserProfileResponse(
    val id: String,
    val name: String,
    val email: String,
    val preferences: UserPreferences? = null,
    val isSuccessful: Boolean = true
)