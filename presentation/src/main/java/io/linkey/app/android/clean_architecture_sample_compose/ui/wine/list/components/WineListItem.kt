package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.linkey.app.android.domain.entity.wine.Rating
import io.linkey.app.android.domain.entity.wine.Wine

@Composable
fun WineListItem(
    sample : Wine,
    onItemClick : (Wine) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clickable { onItemClick(sample) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${sample.id}")
        Text(text = "${sample.wine}", overflow = TextOverflow.Ellipsis)
        Text(text = "${sample.rating.average}")
    }
}

@Preview(name = "와인 목록 아이템 UI", showBackground = true)
@Composable
fun WineListItemPreview() {
    WineListItem(Wine(1,"", Rating("",""),"reds")) {

    }
}