package pt.iade.ei.bestumbrella1.ui.theme



import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = darkColorScheme()

@Composable
fun BestUmbrella1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
