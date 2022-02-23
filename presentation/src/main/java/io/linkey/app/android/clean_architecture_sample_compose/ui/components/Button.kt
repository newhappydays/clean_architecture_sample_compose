package io.linkey.app.android.clean_architecture_sample_compose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.imePadding

@Composable
fun SaveButton(
    isEnable : Boolean = true,
    doSomething : () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .height(62.dp),
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isEnable) {
                Color.Black
            } else {
                Color.LightGray
            }
        ),
        onClick = doSomething,
    ) {
        Text(
            style = TextStyle(color = Color.White),
            text = "저장"
        )
    }
}

@Composable
fun DeleteButton(doSomething : () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .height(62.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        onClick = doSomething,
    ) {
        Text(
            style = TextStyle(color = Color.Black),
            text = "삭제"
        )
    }
}

@Composable
fun ModifyButton(doSomething : () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .height(62.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black
        ),
        onClick = doSomething,
    ) {
        Text(
            style = TextStyle(color = Color.White),
            text = "수정"
        )
    }
}