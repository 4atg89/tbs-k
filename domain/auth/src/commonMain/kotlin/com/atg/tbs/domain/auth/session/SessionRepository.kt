package com.atg.tbs.domain.auth.session

import kotlinx.coroutines.flow.StateFlow

interface SessionRepository {
    val sessionOn: StateFlow<Boolean>
    var token: String?
    var refreshToken: String?
}