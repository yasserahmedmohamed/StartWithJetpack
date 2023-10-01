package com.example.startwithjetpack.navgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.startwithjetpack.presentation.navigator.NewsNavigator
import com.example.startwithjetpack.presentation.onBoarding.OnBoardingViewModel
import com.example.startwithjetpack.presentation.onBoarding.components.OnBoardingScreen
import com.example.startwithjetpack.presentation.search.SearchNewsViewModel
import com.example.startwithjetpack.presentation.search.SearchScreen


@Composable
fun AppGraph(
    startDestination: Route
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::saveFirstEnter)
            }
        }


        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {

                NewsNavigator()


            }
        }
    }
}