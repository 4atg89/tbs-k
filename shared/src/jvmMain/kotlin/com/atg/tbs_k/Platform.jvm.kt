package com.atg.tbs_k


class DesktopPlatform : Platform {
    override val name: String = "DesktopPlatform "
}

actual fun getPlatform(): Platform {
    return DesktopPlatform()
}