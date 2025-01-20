package sdk.meizu.auth.callback;

import sdk.meizu.auth.OAuthError;
import sdk.meizu.auth.OAuthToken;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/callback/AuthCallback.class */
public interface AuthCallback {
    void onError(OAuthError oAuthError);

    void onGetCode(String str);

    void onGetToken(OAuthToken oAuthToken);
}
