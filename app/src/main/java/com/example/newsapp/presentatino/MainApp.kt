package com.example.newsapp.presentatino

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remot.Article
import com.example.newsapp.data.remot.Source
import com.example.newsapp.presentatino.Routs.Detail
import com.example.newsapp.presentatino.Routs.Home
import com.example.newsapp.presentatino.Routs.InnerHome
import com.example.newsapp.presentatino.Routs.Save
import com.example.newsapp.presentatino.Routs.SearcherResult
import com.example.newsapp.presentatino.Routs.WebPage
import com.example.newsapp.presentatino.screens.DetailScreen
import com.example.newsapp.presentatino.screens.HomeScreen
import com.example.newsapp.presentatino.screens.SearchScreen
import com.example.newsapp.presentatino.screens.WebViewScreen
import com.example.newsapp.presentatino.screens.component.BottomBar
import com.example.newsapp.presentatino.viewModel.MainViewModel
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
            // Navigation for Home Screen
            navigation(
                route = Home,
                startDestination = InnerHome
            ){
                // inner home Screen
                composable(InnerHome){
                    HomeScreen(articals =  topHeadLine, modifier = Modifier, onSearch = { search ->
                        if (search.isNotBlank()) {
                            navController.navigate("$SearcherResult/${search}")
                        }
                    }
                    ){article->
                        // Navigate to Detail Screen
                        try {
                            navController.currentBackStackEntry?.savedStateHandle?.set("Article", article)
                            navController.navigate(Detail)
                        }catch (e: Exception){
                            Log.d("Article", e.message?:"")
                        }
                    }
                }
                // Search Screen
                composable("$SearcherResult/{Search}", listOf(navArgument("Search"){type = NavType.StringType})){
                    val search = it.arguments?.getString("Search")
                    SearchScreen(search.toString(),viewModel){article->
                        try {
                            navController.currentBackStackEntry?.savedStateHandle?.set("Article", article)
                            navController.navigate(Detail)
                        }catch (e: Exception){
                            Log.d("Article", e.message?:"")
                        }
                    }
                }
                // Where user click on article it go to Detail Screen
                composable(
                    route = Detail,
                ){
                    val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("Article")?: Article("", "", "", "",
                        Source("",""),"","","")
                    DetailScreen(article,{navController.popBackStack()}){URL->
                        val encodedURL = URLEncoder.encode(URL, StandardCharsets.UTF_8.toString())
                        navController.navigate("$WebPage/${encodedURL}")
                    }
                }
            }
            // All Book mark Screen
            composable(Save){

            }
            composable("$WebPage/{URL}",listOf(navArgument("URL"){type= NavType.StringType})){
                val encodedURL = it.arguments?.getString("URL")
                 encodedURL?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                    WebViewScreen(it)
                }
            }
        }
    }
}
