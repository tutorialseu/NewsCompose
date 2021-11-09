package eu.tutorials.newsapp.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import eu.tutorials.newsapp.BottomMenuScreen
import eu.tutorials.newsapp.R

//Todo 5: create a component package and create a file with composable for BottomMenu
@Composable
fun BottomMenu(navController:NavController) {
    //Todo 6: create a list for the menu items using the BottomMenuScreen sealed class
    val menuItems = listOf(
        BottomMenuScreen.TopNews,
        BottomMenuScreen.Categories,
        BottomMenuScreen.Sources
    )
    //Todo 7: Add th BottomNavigation, loop through each item and set to bottom navigation item
    BottomNavigation(contentColor = colorResource(id = R.color.white))
    {
        menuItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title) },
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },

                )

        }
    }
}