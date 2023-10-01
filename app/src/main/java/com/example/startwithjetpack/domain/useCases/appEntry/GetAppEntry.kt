package com.example.startwithjetpack.domain.useCases.appEntry

import com.example.startwithjetpack.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.getAppEntryState()
    }
}