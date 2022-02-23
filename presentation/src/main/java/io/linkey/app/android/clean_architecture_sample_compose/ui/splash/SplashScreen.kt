package io.linkey.app.android.clean_architecture_sample_compose.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import io.linkey.app.android.clean_architecture_sample_compose.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Text("시발")
        LaunchedEffect(key1 = true) {
            delay(1000)
            navController.navigate(Route.MainScreen.route) {
                popUpTo(Route.SplashScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SplashScreen(rememberNavController())
//}