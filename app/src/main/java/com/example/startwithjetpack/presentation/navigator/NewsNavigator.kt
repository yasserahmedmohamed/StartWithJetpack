package com.example.startwithjetpack.presentation.navigator

import android.content.Context
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.startwithjetpack.R
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.navgraph.Route
import com.example.startwithjetpack.presentation.bookmark.BookmarkScreen
import com.example.startwithjetpack.presentation.bookmark.BookmarkViewModel
import com.example.startwithjetpack.presentation.common.BottomNavigationItem
import com.example.startwithjetpack.presentation.common.NewsBottomNavigation
import com.example.startwithjetpack.presentation.details.DetailsScreen
import com.example.startwithjetpack.presentation.details.DetailsViewModel
import com.example.startwithjetpack.presentation.home.HomeScreen
import com.example.startwithjetpack.presentation.home.HomeViewModel
import com.example.startwithjetpack.presentation.search.SearchNewsViewModel
import com.example.startwithjetpack.presentation.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClicked = {
                        when (it) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val tm =
                    LocalContext.current.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val countryCodeValue = tm.networkCountryIso
                HomeScreen(
                    articles = homeViewModel
                        .getTopHeadLines("eg")
                        .collectAsLazyPagingItems(),
                    navigateToDetails = {
                        navigateToDetails(navController, it)
                    })
            }


            composable(route = Route.SearchScreen.route) {
                onBackClick(navController = navController)
                val searchViewModel: SearchNewsViewModel = hiltViewModel()
                SearchScreen(
                    state = searchViewModel.state.value,
                    event = searchViewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(navController, it)
                    })
            }

            composable(route = Route.BookmarkScreen.route) {
                onBackClick(navController = navController)

                val viewModel: BookmarkViewModel = hiltViewModel()

                BookmarkScreen(list = viewModel.articles.value, navigateToDetails = {
                    navigateToDetails(navController, it)
                })
            }

            composable(route = Route.DetailsScreen.route) {

                val viewModel: DetailsViewModel = hiltViewModel()
                val context = LocalContext.current

                LaunchedEffect(key1 = viewModel.message) {
                    if (viewModel.message!=null)
                    Toast.makeText(context, viewModel.message, Toast.LENGTH_SHORT).show()
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("Article")
                    ?.let {

                        DetailsScreen(
                            article = it,
                            saveEvent = {
                                viewModel.saveArticle(it)
                            },
                            navigateUp = { navController.navigateUp() }
                        )

                    }

            }

        }
    }
}


@Composable
fun onBackClick(navController: NavController) {
    BackHandler(enabled = true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("Article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}