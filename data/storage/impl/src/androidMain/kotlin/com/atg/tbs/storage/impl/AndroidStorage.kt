package com.atg.tbs.storage.impl

import android.content.Context
import android.content.SharedPreferences
import com.atg.tbs.storage.impl.PlatformStorage.Companion.TOKEN_KEY
import com.atg.tbs.storage.impl.PlatformStorage.Companion.REFRESH_TOKEN_KEY
import com.atg.tbs.storage.impl.PlatformStorage.Companion.TOKEN_PREFS

internal class AndroidStorage(context: Context) : PlatformStorage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE)

    override var token: String?
        get() = prefs.getString(TOKEN_KEY, null)
        set(value) { prefs.edit().putString(TOKEN_KEY, value).apply() }

    override var refreshToken: String?
        get() = prefs.getString(REFRESH_TOKEN_KEY, null)
        set(value) { prefs.edit().putString(REFRESH_TOKEN_KEY, value).apply() }

}