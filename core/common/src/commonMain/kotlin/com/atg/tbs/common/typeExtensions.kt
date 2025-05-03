package com.atg.tbs.common

inline fun <reified T : Any> Any.runIfType(action: (T) -> Unit) = (this as? T?)?.run(action)

//@Throws
fun <T> tryOrThrow(value: () -> T, block: (Exception) -> Exception): T =
    try { value.invoke() } catch (e: Exception) { throw block(e) }

fun <T> tryOrNull(value: () -> T): T? =
    try { value.invoke() } catch (e: Exception) { null }
