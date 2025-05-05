package com.atg.tbs.auth.verify

import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.domain.auth.auth.AuthInteractor
import kotlinx.coroutines.launch

class VerifyScreenModel(private val authInteractor: AuthInteractor) : BaseScreenModel, BaseScreenModelImpl() {

    fun confirm(code: String) {
        scope.launch {
            authInteractor.confirmLogin(code)
        }
    }
}