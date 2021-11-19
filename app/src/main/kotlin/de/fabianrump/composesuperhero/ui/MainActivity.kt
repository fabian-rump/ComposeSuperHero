package de.fabianrump.composesuperhero.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import de.fabianrump.composesuperhero.ui.navigation.NavigationComponent
import de.fabianrump.composesuperhero.ui.theme.SuperHeroTheme
import de.fabianrump.navigation.Navigator
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme {
                val navigator = get<Navigator>()
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController, navigator = navigator)
                }
            }
        }
    }
}