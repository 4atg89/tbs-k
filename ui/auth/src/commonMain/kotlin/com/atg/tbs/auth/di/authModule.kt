package com.atg.tbs.auth.di

import com.atg.tbs.auth.login.LoginScreenModel
import com.atg.tbs.auth.register.RegistrationScreenModel
import com.atg.tbs.auth.verify.VerifyScreenModel
import com.atg.tbs.data.auth.authDataModule
import com.atg.tbs.domain.auth.authDomainModel
import org.koin.dsl.module

fun authModule() = module {
    includes(authDataModule(), authDomainModel())
    factory { LoginScreenModel(get()) }
    factory { VerifyScreenModel(get()) }
    factory { RegistrationScreenModel(get()) }
}