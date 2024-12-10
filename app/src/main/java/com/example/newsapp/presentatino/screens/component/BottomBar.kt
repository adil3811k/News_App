package com.example.newsapp.presentatino.screens.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.presentatino.Routs.Home
import com.example.newsapp.presentatino.Routs.InnerHome
import com.example.newsapp.presentatino.Routs.Save

private data class BottomNavHelper(
    val icon: ImageVector,
    val tital: String,
)
private  val list = listOf(
    BottomNavHelper(
        icon = Icons.Default.Home,
        // I use innerHome as rout because I use nested navigation and with nested navigation only home not working
        tital = InnerHome,
    ),
    BottomNavHelper(
        icon = Icons.Default.Star,
        tital = Save,
    ),
)

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar{
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        Log.d("Navigation", currentDestination?.route.toString())
        list.forEach {item->
            NavigationBarItem(
                selected =currentDestination?.route == item.tital,
                onClick = {
                    navController.navigate(item.tital) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                icon = { Icon(item.icon , null) }
                )
        }
    }
}