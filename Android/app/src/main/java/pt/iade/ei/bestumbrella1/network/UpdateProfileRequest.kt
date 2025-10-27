package pt.iade.ei.bestumbrella1.network

data class UpdateProfileRequest(
    val name: String? = null,
    val preferences: UserPreferences? = null
)