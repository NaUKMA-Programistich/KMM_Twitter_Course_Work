package ua.ukma.edu.programistich.kmmtwitter.android.login

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.identity.OAuthActivity
import ua.ukma.edu.programistich.kmmtwitter.auth.models.TwitterAuthData
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginEvent
import ua.ukma.edu.programistich.kmmtwitter.login.models.LoginState

const val EXTRA_TWITTER_LOGIN = "auth_config"
const val EXTRA_TOKEN = "tk"
const val EXTRA_TOKEN_SECRET = "ts"
const val EXTRA_USER_ID = "user_id"
const val DEFAULT_USER_ID = -1L

@Composable
internal fun LoginView(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    val context = LocalContext.current
    val twitterLoginLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val twitterAuth = result.toTwitterAuthResult()
        onEvent(LoginEvent.LoginSuccess(twitterAuth))
    }

    Column {
        Text(text = "Login Screen")
        when (state) {
            LoginState.EntryDisplay -> {
                Button(
                    onClick = {
                        val intent = Intent(context, OAuthActivity::class.java)
                        val config = Twitter.getInstance().twitterAuthConfig
                        intent.putExtra(EXTRA_TWITTER_LOGIN, config)
                        onEvent(LoginEvent.ClickLogin)
                        twitterLoginLauncher.launch(intent)
                    }
                ) {
                    Text(text = "Login via Twitter")
                }
            }
            LoginState.Loading -> CircularProgressIndicator()
            LoginState.Success, LoginState.Error -> {}
        }
    }
}

private fun ActivityResult.toTwitterAuthResult(): TwitterAuthData? {
    val data = this.data ?: return null
    if (this.resultCode != OAuthActivity.RESULT_OK) return null

    val token = data.getStringExtra(EXTRA_TOKEN) ?: return null
    val tokenSecret = data.getStringExtra(EXTRA_TOKEN_SECRET) ?: return null
    val userId = data.getLongExtra(EXTRA_USER_ID, DEFAULT_USER_ID)
    if (userId == DEFAULT_USER_ID) return null

    return TwitterAuthData(token, tokenSecret, userId.toString())
}
