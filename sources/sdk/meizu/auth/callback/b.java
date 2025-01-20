package sdk.meizu.auth.callback;

import sdk.meizu.auth.IAuthCallback;
import sdk.meizu.auth.OAuthError;
import sdk.meizu.auth.OAuthToken;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/callback/b.class */
public class b extends IAuthCallback.Stub {
    private AuthCallback a;

    public b(AuthCallback authCallback) {
        this.a = authCallback;
    }

    @Override // sdk.meizu.auth.IAuthCallback
    public void a(OAuthError oAuthError) {
        if (this.a != null) {
            this.a.onError(oAuthError);
        }
    }

    @Override // sdk.meizu.auth.IAuthCallback
    public void a(String str) {
        if (this.a != null) {
            this.a.onGetCode(str);
        }
    }

    @Override // sdk.meizu.auth.IAuthCallback
    public void a(OAuthToken oAuthToken) {
        if (this.a != null) {
            this.a.onGetToken(oAuthToken);
        }
    }

    public void a() {
        this.a = null;
    }
}
