package dev.fabled.authorization_feature.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.api.ApiException
import dev.fabled.authorization_feature.AuthorizationViewModel
import dev.fabled.authorization_feature.R
import dev.fabled.common.components.DrawLogo
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.common.utils.isLoading
import dev.fabled.domain.model.Resource
import timber.log.Timber

@Composable
fun AuthorizationScreen(
    authorizationViewModel: AuthorizationViewModel
) {
    val context = LocalContext.current

    val signInResult = authorizationViewModel.signInResult
    val authenticationResult = authorizationViewModel.authenticationResult

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials =
                    authorizationViewModel.signInClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                authorizationViewModel.authorizeUser(googleIdToken = googleIdToken)
            } catch (exception: ApiException) {
                Timber.e(exception)
                showErrorToast(context)
            }
        }
    }

    when (signInResult) {
        is Resource.Error -> showErrorToast(context)
        is Resource.Success -> {
            IntentSenderRequest.Builder(signInResult.data.pendingIntent.intentSender)
                .build()
                .let(signInLauncher::launch)
        }
        else -> Unit
    }

    when (authenticationResult) {
        Resource.Loading -> AuthenticationLoadingContent(modifier = Modifier.fillMaxSize())
        is Resource.Error -> showErrorToast(context)
        else -> Unit
    }

    AuthenticationScreenContent(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        onSignInClicked = authorizationViewModel::beginSignIn,
        isSignInFormEnabled = !authenticationResult.isLoading()
    )
}

@Composable
fun AuthenticationLoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp), color = DarkPrimary)
    }
}

@Composable
fun AuthenticationScreenContent(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit,
    isSignInFormEnabled: Boolean
) {
    val authenticationFormAlpha = animateFloatAsState(
        targetValue = if (isSignInFormEnabled) 1f else .0f
    )

    Box(
        modifier = modifier.alpha(authenticationFormAlpha.value),
    ) {
        DrawLogo(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = onSignInClicked,
                modifier = Modifier
                    .fillMaxWidth(fraction = .7f)
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                contentPadding = PaddingValues(15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    modifier = Modifier.padding(end = 15.dp),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(R.string.google_sign_in),
                    color = DarkPrimary,
                    fontFamily = SegoeUi,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Text(
                text = buildAnnotatedString {
                    append(
                        text = AnnotatedString(
                            text = stringResource(R.string.by_sign_in),
                            spanStyle = SpanStyle(color = Color.Gray)
                        )
                    )
                    append(
                        text = AnnotatedString(
                            text = stringResource(R.string.user_agreement),
                            SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                        )
                    )
                    append(
                        text = AnnotatedString(
                            text = stringResource(R.string.and),
                            spanStyle = SpanStyle(color = Color.Gray)
                        )
                    )
                    append(
                        text = AnnotatedString(
                            text = stringResource(R.string.privacy_policy),
                            SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                        )
                    )
                },
                fontFamily = SegoeUi,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

private fun showErrorToast(context: Context) = Toast.makeText(
    context,
    context.getString(R.string.authorization_error),
    Toast.LENGTH_SHORT
).show()