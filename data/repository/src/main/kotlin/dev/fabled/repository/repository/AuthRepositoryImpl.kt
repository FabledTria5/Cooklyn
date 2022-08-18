package dev.fabled.repository.repository

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import dev.fabled.domain.model.Resource
import dev.fabled.domain.repository.authentication.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val signInClient: SignInClient,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    companion object {
        private const val USERS_COLLECTION = "users_collection"

        private const val USER_NAME = "user_name"
        private const val USER_IMAGE = "user_image"
        private const val USER_EMAIL = "user_email"
    }

    override val displayName = firebaseAuth.currentUser?.displayName.orEmpty()
    override val userPhotoUrl = firebaseAuth.currentUser?.photoUrl.toString()

    override fun authenticateWithFirebase(authorizationToken: String?) = flow {
        try {
            emit(Resource.Loading)

            val googleCredentials = GoogleAuthProvider.getCredential(authorizationToken, null)
            val authResult = firebaseAuth.signInWithCredential(googleCredentials).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                firebaseAuth.currentUser?.let { user ->
                    firestore.collection(USERS_COLLECTION)
                        .document(user.uid)
                        .set(user.toFirestore())
                        .await()
                }
                emit(Resource.Success(data = true))
            }
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.Error(error = exception.message.orEmpty()))
        }
    }

    override fun signOut() = flow {
        try {
            emit(Resource.Loading)
            signInClient.signOut()
            firebaseAuth.signOut()
            emit(Resource.Success(data = true))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.Error(error = exception.message.orEmpty()))
        }
    }

    override fun isUserAuthenticated() = callbackFlow {
        val authStateListener = AuthStateListener {
            trySend(Resource.Success(data = firebaseAuth.currentUser != null))
        }
        firebaseAuth.addAuthStateListener(authStateListener)

        awaitClose { firebaseAuth.removeAuthStateListener(authStateListener) }
    }

    private fun FirebaseUser.toFirestore() = hashMapOf(
        USER_NAME to displayName,
        USER_IMAGE to photoUrl.toString(),
        USER_EMAIL to email
    )

}