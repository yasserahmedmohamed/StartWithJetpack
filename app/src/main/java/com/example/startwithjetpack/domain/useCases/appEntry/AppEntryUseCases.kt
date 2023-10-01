package com.example.startwithjetpack.domain.useCases.appEntry

data class AppEntryUseCases(
    val read: GetAppEntry,
    val write: SaveAppEntry
)