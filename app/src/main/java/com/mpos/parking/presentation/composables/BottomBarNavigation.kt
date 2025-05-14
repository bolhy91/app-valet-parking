package com.mpos.parking.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mpos.parking.root.MainNavigation

@Composable
fun BottomBarNavigation(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { it }
        ) + fadeOut(),
    ) {
        NavigationBar {
            val items = listOf(
                MainNavigation.Home,
                MainNavigation.Entry,
                MainNavigation.Record
            )
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.hasRoute(item.route::class)
                    } == true,
                    label = { Text(text = item.title) },
                    onClick = {
                        navController.navigate(item.route) {
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
                            imageVector = (if (currentDestination?.hierarchy?.any {
                                    it.hasRoute(item.route::class)
                                } == true) item.selectedIcon else item.unSelectedIcon),
                            item.title
                        )
                    })
            }
        }
    }
}
