package io.linkey.app.android.clean_architecture_sample_compose.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import io.linkey.app.android.clean_architecture_sample_compose.Route

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        TextButton(
            onClick = {
                navController.navigate(Route.WineListScreen.route)
            }
        ) {
            Text(text = "API Sample Page")
        }

        TextButton(
            onClick = {
                navController.navigate(Route.TodoListScreen.route)
            }
        ) {
            Text(text = "Room Sample Page")
        }

    }
}