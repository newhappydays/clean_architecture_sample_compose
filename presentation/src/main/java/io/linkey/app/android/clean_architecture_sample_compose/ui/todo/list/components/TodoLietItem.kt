package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.Route
import io.linkey.app.android.domain.entity.todo.Todo

@Composable
fun TodoListItem(
     navController : NavController,
     todo : Todo
) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(10.dp))
        .padding(10.dp)
        .border(BorderStroke(2.dp, Color.Black))
        .clickable { navController.navigate(Route.TodoDetailScreen.route + "/${todo.uid}")  },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = todo.title)
        }
    }
}

@Preview("Todo Item", showBackground = true)
@Composable
fun TodoListItemPreview() {
    TodoListItem(
        rememberNavController(),
        Todo(0, "샘플타이틀", "샘플내용", "")
    )
}