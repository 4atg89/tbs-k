package com.atg.tbs.storage.impl

import platform.Foundation.NSUserDefaults
import com.atg.tbs.storage.impl.PlatformStorage.Companion.TOKEN_KEY
import com.atg.tbs.storage.impl.PlatformStorage.Companion.REFRESH_TOKEN_KEY

internal class IosStorage : PlatformStorage {

    override var token: String?
        get() = defaults.stringForKey(TOKEN_KEY)
        set(value) { defaults.setObject(value, forKey = TOKEN_KEY) }

    override var refreshToken: String?
        get() = defaults.stringForKey(REFRESH_TOKEN_KEY)
        set(value) { defaults.setObject(value, forKey = REFRESH_TOKEN_KEY) }

    private val defaults: NSUserDefaults get() = NSUserDefaults.standardUserDefaults
}