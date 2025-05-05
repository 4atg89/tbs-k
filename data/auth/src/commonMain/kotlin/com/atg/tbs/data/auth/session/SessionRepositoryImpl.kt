package com.atg.tbs.data.auth.session

import com.atg.tbs.domain.auth.session.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow

class SessionRepositoryImpl : SessionRepository {
    override val sessionOn = MutableStateFlow(false)
}