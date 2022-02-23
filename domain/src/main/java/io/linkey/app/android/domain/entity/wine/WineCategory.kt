package io.linkey.app.android.domain.entity.wine

data class WineCategory(
    val id : Long,
    val title : String,
    val isChecked : Boolean
) {
    companion object {
        val sample = listOf(
            WineCategory(0,"reds", false),
            WineCategory(1,"whites", false),
            WineCategory(2,"sparkling", false),
            WineCategory(3,"rose", false),
            WineCategory(4,"dessert", false),
            WineCategory(5,"port", false),
        )
    }
}
