package com.atg.tbs.ui.dashboard

import com.atg.tbs.account.AccountInteractor
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import kotlinx.coroutines.launch

class DashboardScreenModel(
    private val accountInteractor: AccountInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    init {
        scope.launch {
            accountInteractor.loadAccount()
        }
    }
}