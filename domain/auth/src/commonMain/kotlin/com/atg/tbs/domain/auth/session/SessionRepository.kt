package com.atg.tbs.domain.auth.session

import com.atg.tbs.domain.auth.model.TokenEntity
import kotlinx.coroutines.flow.StateFlow

interface SessionRepository {
    val sessionOn: StateFlow<Boolean>
    var token: TokenEntity?
}