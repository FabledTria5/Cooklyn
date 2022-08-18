package dev.fabled.common.utils

import dev.fabled.domain.model.Resource

fun <T> Resource<T>.isLoading() = this is Resource.Loading