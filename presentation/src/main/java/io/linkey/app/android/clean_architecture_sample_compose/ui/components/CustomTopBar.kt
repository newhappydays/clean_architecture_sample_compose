package io.linkey.app.android.clean_architecture_sample_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.R

@Composable
fun CustomTopBar(navController: NavController) {
    TopAppBar {
        Row {
           IconButton(onClick = { navController.popBackStack() }) {
               Image(
                   painterResource(id = R.drawable.ic_back),
                   contentDescription = null
               )
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTopBarPreview() {
    CustomTopBar(rememberNavController())
}