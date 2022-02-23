package io.linkey.app.android.clean_architecture_sample_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import io.linkey.app.android.clean_architecture_sample_compose.ui.main.MainScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.detail.SampleDetailScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.SampleListScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.splash.SplashScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.theme.Clean_architecture_sample_composeTheme
import io.linkey.app.android.clean_architecture_sample_compose.ui.todo.detail.TodoDetailScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.todo.edit.TodoEditScreen
import io.linkey.app.android.clean_architecture_sample_compose.ui.todo.list.TodoListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Clean_architecture_sample_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.SplashScreen.route
                    ) {
                        composable(route = Route.SplashScreen.route) {
                            SplashScreen(navController)
                        }
                        composable(route = Route.MainScreen.route) {
                            MainScreen(navController)
                        }
                        composable(route = Route.WineListScreen.route) {
                            SampleListScreen(navController)
                        }
                        composable(route = Route.WineDetailScreen.route + "/{wine}/{wineId}") {
                            SampleDetailScreen(navController)
                        }
                        composable(route = Route.TodoListScreen.route) {
                            TodoListScreen(navController)
                        }
                        composable(route = Route.TodoDetailScreen.route + "/{todoId}") {
                            TodoDetailScreen(navController)
                        }
                        composable(route = Route.TodoEditScreen.route + "/{todoId}") {
                            TodoEditScreen(navController)
                        }
                    }
                }
            }
        }
    }
}