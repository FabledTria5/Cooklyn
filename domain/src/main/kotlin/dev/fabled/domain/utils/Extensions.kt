package dev.fabled.domain.utils

suspend fun String.ifNotEmpty(block: suspend (String) -> Unit): String {
    if (isNotEmpty()) block(this)
    return this
}