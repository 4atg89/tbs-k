package com.atg.tbs_k


sealed interface SplashAction

data object CheckAuthentication : SplashAction

data class SplashState(val isLogin: Boolean? = null)
