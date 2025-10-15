package pt.iade.ei.bestumbrella1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pt.iade.ei.bestumbrella1.MainNavigation.MainNavigation
import pt.iade.ei.bestumbrella1.ui.theme.BestUmbrella1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BestUmbrella1Theme {
                MainNavigation()
            }
        }
    }
}
