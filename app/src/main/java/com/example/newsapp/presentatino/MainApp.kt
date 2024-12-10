package com.example.newsapp.presentatino

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentatino.Routs.Home
import com.example.newsapp.presentatino.Routs.InnerHome
import com.example.newsapp.presentatino.Routs.Save
import com.example.newsapp.presentatino.Routs.SearcherResult
import com.example.newsapp.presentatino.screens.HomeScreen
import com.example.newsapp.presentatino.screens.SearchScreen
import com.example.newsapp.presentatino.screens.component.BottomBar
import com.example.newsapp.presentatino.viewModel.MainViewModel

@Composable
fun MainApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MainViewModel>()
    val topHeadLine = viewModel.topHeadLine.collectAsLazyPagingItems()
    Scaffold (
        bottomBar = { BottomBar(navController) }
    ){padding->
        NavHost(navController = navController , startDestination = Home , modifier = Modifier.padding(padding)) {
            navigation(
                route = Home,
                startDestination = InnerHome
            ){
                composable(InnerHome){
                    HomeScreen(topHeadLine) { }
                }
                composable(SearcherResult){
                    SearchScreen(modifier.padding(padding))
                }
            }
            composable(Save){

            }
        }
    }
}