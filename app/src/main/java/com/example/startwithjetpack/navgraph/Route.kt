package com.example.startwithjetpack.navgraph

sealed class Route(val route:String){
    object OnBoardingScreen:Route("onBoarding")
    object HomeScreen:Route("home")
    object SearchScreen:Route("search")
    object BookmarkScreen:Route("bookmark")
    object DetailsScreen:Route("details")

    object AppStartNavigation:Route("appStartNavigation")
    object NewsNavigation:Route("newsNavigation")

    object NewsNavigatorScreen:Route("newsNavigatorScreen")

}
