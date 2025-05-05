package com.atg.tbs.storage.impl

import java.util.prefs.Preferences
import com.atg.tbs.storage.impl.PlatformStorage.Companion.TOKEN_PREFS
import com.atg.tbs.storage.impl.PlatformStorage.Companion.TOKEN_KEY
import com.atg.tbs.storage.impl.PlatformStorage.Companion.REFRESH_TOKEN_KEY

internal class DesktopStorage : PlatformStorage {

    private val prefs = Preferences.userRoot().node(TOKEN_PREFS)

    override var token: String?
        get() = prefs.get(TOKEN_KEY, null)
        set(value) { prefs.put(TOKEN_KEY, value) }

    override var refreshToken: String?
        get() = prefs.get(REFRESH_TOKEN_KEY, null)
        set(value) { prefs.put(REFRESH_TOKEN_KEY, value) }

}