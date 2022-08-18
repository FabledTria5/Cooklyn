package dev.fabled.domain.repository.home

import dev.fabled.domain.repository.authentication.AuthRepository
import javax.inject.Inject

class GetUserName @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.displayName
        .split(" ")
        .first()

}