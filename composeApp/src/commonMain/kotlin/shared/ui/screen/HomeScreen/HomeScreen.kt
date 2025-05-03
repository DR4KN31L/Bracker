package shared.ui.screen.HomeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import shared.routes.HomeNavigator
import shared.routes.Routes
import shared.ui.screen.composables.TexturedBackground


// Android Documentation : https://developer.android.com/develop/ui/compose/navigation#bottom-nav
@Composable
fun HomeScreen(userId: Int) {
    val bottomNavController = rememberNavController()

    val items = listOf(
        Routes.NavDestination.Profile, Routes.NavDestination.Home, Routes.NavDestination.Charts
    )
    TexturedBackground {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Transparent,
                    tonalElevation = 0f.dp
                ) {
                    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(resource = screen.drawableRes),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(34.dp)
                                ) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                bottomNavController.navigate(screen.route) {
                                    popUpTo(bottomNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            HomeNavigator(
                userId = userId,
                navController = bottomNavController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}