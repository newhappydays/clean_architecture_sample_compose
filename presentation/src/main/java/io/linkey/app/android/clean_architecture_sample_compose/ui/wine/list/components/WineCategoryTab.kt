package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.linkey.app.android.domain.entity.wine.WineCategory

@Composable
fun WineCategoryTab(
    wine : WineCategory,
    onItemClick : (String) -> Unit
) {
    Box(
        modifier = Modifier.padding(
            horizontal = 10.dp,
            vertical = 15.dp
        )
    ) {
        TextButton(onClick = { onItemClick(wine.title) }) {
            Text(
                text = wine.title,
                color = if (wine.isChecked) {
                    Color.Red
                } else {
                    Color.Black
                }
            )
        }
    }
}

@Preview
@Composable
fun WineCategoryTabPreview() {
    WineCategoryTab(WineCategory.sample[0]) {

    }
}