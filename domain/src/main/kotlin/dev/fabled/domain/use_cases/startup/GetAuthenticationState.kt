package dev.fabled.domain.use_cases.startup

import dev.fabled.domain.repository.authentication.AuthRepository
import javax.inject.Inject

class GetAuthenticationState @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.isUserAuthenticated

}