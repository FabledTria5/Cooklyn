package dev.fabled.domain.use_cases.authorization

import dev.fabled.domain.repository.authentication.AuthRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.isUserAuthenticated()

}