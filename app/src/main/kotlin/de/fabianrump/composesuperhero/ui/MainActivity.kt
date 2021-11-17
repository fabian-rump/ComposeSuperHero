package de.fabianrump.composesuperhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import org.koin.androidx.compose.get
import androidx.navigation.compose.rememberNavController
import de.fabianrump.composesuperhero.ui.navigation.NavigationComponent
import de.fabianrump.composesuperhero.ui.theme.SuperHeroTheme
import de.fabianrump.navigation.Navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme {
                val navigator = get<Navigator>()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController, navigator = navigator)
                }
            }
        }
    }
}