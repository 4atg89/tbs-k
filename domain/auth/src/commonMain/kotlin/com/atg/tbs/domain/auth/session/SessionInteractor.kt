package com.atg.tbs.domain.auth.session

import kotlinx.coroutines.flow.StateFlow

interface SessionInteractor {
    val sessionOn: StateFlow<Boolean>
}

internal class SessionInteractorImpl(private val repository: SessionRepository) : SessionInteractor {

    override val sessionOn: StateFlow<Boolean>
        get() = repository.sessionOn

}