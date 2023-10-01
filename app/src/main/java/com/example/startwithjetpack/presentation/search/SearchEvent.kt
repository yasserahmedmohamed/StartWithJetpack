package com.example.startwithjetpack.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val q: String) : SearchEvent()
    object SearchNews : SearchEvent()
}
