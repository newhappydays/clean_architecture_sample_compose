package io.linkey.app.android.clean_architecture_sample_compose

sealed class Route(val route: String) {
    object SplashScreen: Route("splash_screen")
    object MainScreen: Route("main_screen")
    object WineListScreen: Route("wine_list_screen")
    object WineDetailScreen: Route("wine_detail_screen")
    object TodoListScreen: Route("todo_list_screen")
    object TodoDetailScreen: Route("todo_detail_screen")
    object TodoEditScreen: Route("todo_edit_screen")
}