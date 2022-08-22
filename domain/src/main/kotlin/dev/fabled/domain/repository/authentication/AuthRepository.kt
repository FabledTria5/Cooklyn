package dev.fabled.domain.repository.authentication

import dev.fabled.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val displayName: String
    val userPhotoUrl: String
    val isUserAuthenticated: Boolean

    fun authenticateWithFirebase(authorizationToken: String?): Flow<Resource<Boolean>>
    fun isUserAuthenticated(): Flow<Resource<Boolean>>
    fun signOut(): Flow<Resource<Boolean>>

}