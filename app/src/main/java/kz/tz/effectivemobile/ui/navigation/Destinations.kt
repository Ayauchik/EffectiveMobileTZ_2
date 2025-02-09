package kz.tz.effectivemobile.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import kz.tz.effectivemobile.R

sealed class Screens(val route : String) {
    object Search : Screens("search")
    object Profile : Screens("profile")
    object Favourite: Screens("favourite")
    object Messages: Screens("messages")
    object Response: Screens("response")
    object Vacancies: Screens("vacancies")
}

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label : String = "",
    val icon : Int = 0,
    val iconFilled: Int? = 0,
    val route : String = "",
    val badgeCount: Int? = null,
    val hasNews: Boolean = false
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Поиск",
                icon = R.drawable.ic_search,
                route = Screens.Search.route,
                iconFilled = 0,
                badgeCount = null
            ),
            BottomNavigationItem(
                label = "Профиль",
                icon = R.drawable.ic_profile,
                route = Screens.Profile.route,
                iconFilled = 0,
                badgeCount = null
            ),
            BottomNavigationItem(
                label = "Избранное",
                icon = R.drawable.ic_heart,
                route = Screens.Favourite.route,
                iconFilled = R.drawable.ic_heart_filled,
                badgeCount = 1,
                hasNews = true
            ),
            BottomNavigationItem(
                label = "Сообщения",
                icon = R.drawable.ic_message,
                route = Screens.Messages.route,
                iconFilled = 0,
                badgeCount = null
            ),
            BottomNavigationItem(
                label = "Отклики",
                icon  = R.drawable.ic_response,
                route = Screens.Response.route,
                iconFilled = 0,
                badgeCount = null
            ),

        )
    }
}