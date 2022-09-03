package dev.fabled.authorization_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.model.Resource
import dev.fabled.domain.use_cases.authorization.AuthenticateWithToken
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val signInRequest: BeginSignInRequest,
    private val authenticateWithToken: AuthenticateWithToken,
    val signInClient: SignInClient
) : ViewModel() {

    var signInResult by mutableStateOf<Resource<BeginSignInResult>>(Resource.Idle)
        private set

    var authenticationResult by mutableStateOf<Resource<Boolean>>(Resource.Idle)
        private set

    fun beginSignIn() = viewModelScope.launch(Dispatchers.IO) {
        try {
            signInResult = Resource.Loading

            val result = signInClient.beginSignIn(signInRequest).await()
            signInResult = Resource.Success(result)
        } catch (exception: ApiException) {
            Timber.e(exception)
            signInResult = Resource.Error(error = exception.message.toString())
        }
    }

    fun authorizeUser(googleIdToken: String?) = authenticateWithToken(authToken = googleIdToken)
        .onEach { authResult ->
            when (authResult) {
                is Resource.Success ->
                    navigationManager.navigate(PrimaryAppDirections.HomeDirections.home)
                else -> authenticationResult = authResult
            }
        }
        .flowOn(Dispatchers.IO)
        .launchIn(viewModelScope)

}