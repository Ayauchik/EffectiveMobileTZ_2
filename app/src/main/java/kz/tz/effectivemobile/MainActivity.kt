package kz.tz.effectivemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.tz.effectivemobile.ui.favourite.FavouriteScreen
import kz.tz.effectivemobile.ui.messages.MessagesScreen
import kz.tz.effectivemobile.ui.navigation.BottomNavigationItem
import kz.tz.effectivemobile.ui.navigation.Screens
import kz.tz.effectivemobile.ui.profile.ProfileScreen
import kz.tz.effectivemobile.ui.response.ResponseScreen
import kz.tz.effectivemobile.ui.search.SearchScreen
import kz.tz.effectivemobile.ui.search.VacanciesByComplianceScreen
import kz.tz.effectivemobile.ui.theme.EffectiveMobileTheme
import kz.tz.effectivemobile.ui.theme.Grey4
import kz.tz.effectivemobile.ui.theme.Shadows
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EffectiveMobileTheme(
                darkTheme = true
            ) {
                BottomNavigationBar()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(viewModel: MainViewModel = get()) {
    val navController = rememberNavController()
    val favoriteCount by viewModel.favoriteVacanciesCount.collectAsState()

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Shadows,
            ) {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                //getting the list of bottom navigation items for our data class
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->

                        val isSelected = when (navigationItem.route) {
                            Screens.Search.route -> currentRoute == Screens.Search.route || currentRoute == Screens.Vacancies.route
                            else -> currentRoute == navigationItem.route
                        }

                        //iterating all items with their respective indexes
                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(
                                    text = navigationItem.label,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.W400,
                                    lineHeight = 11.sp,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            icon = {

                                BadgedBox(
                                    badge = {
                                        if (navigationItem.label == "Избранное" && favoriteCount > 0) {
                                            Badge { Text(text = favoriteCount.toString()) }
                                        }
                                    }
                                ) {

                                    val icon = if (isSelected && navigationItem.iconFilled != 0) {
                                        navigationItem.iconFilled!!
                                    } else {
                                        navigationItem.icon
                                    }

                                    Icon(
                                        painter = painterResource(icon),
                                        contentDescription = navigationItem.label,
                                        modifier = Modifier.background(Color.Transparent)
                                    )
                                }
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Color.Blue,
                                selectedTextColor = Color.Blue,
                                selectedIndicatorColor = Color.Transparent,
                                unselectedIconColor = Grey4,
                                unselectedTextColor = Grey4,
                                disabledIconColor = Grey4,
                                disabledTextColor = Grey4
                            ),

                        )
                    }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Search.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Response.route) {
                ResponseScreen()
            }
            composable(Screens.Messages.route) {
                MessagesScreen()
            }
            composable(Screens.Search.route) {
                SearchScreen(navController = navController)
            }
            composable(Screens.Vacancies.route){
                VacanciesByComplianceScreen(navController = navController)
            }
            composable(Screens.Profile.route) {
                ProfileScreen()
            }
            composable(Screens.Favourite.route) {
                FavouriteScreen()
            }
        }
    }
}


