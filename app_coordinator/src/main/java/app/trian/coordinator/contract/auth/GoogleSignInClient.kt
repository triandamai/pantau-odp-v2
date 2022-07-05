package app.trian.coordinator.contract.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

val CLIENT_ID = "69888863455-ol6efou6o0ibacfd4b3g1ou1bqj9f16k.apps.googleusercontent.com"
fun getGoogleSignInClient(context: Context):GoogleSignInClient{
    val signInOptions = GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN
    )
    //Request id token if you intend to verify google user from your backend server
      .requestIdToken(CLIENT_ID)
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context,signInOptions)
}