package sdk.meizu.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import sdk.meizu.auth.callback.AccountLoginResponse;
import sdk.meizu.auth.callback.AuthCallback;
import sdk.meizu.auth.callback.AuthResponse;
import sdk.meizu.auth.callback.CodeCallback;
import sdk.meizu.auth.callback.ImplictCallback;
import sdk.meizu.auth.ui.AuthActivity;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/c.class */
public abstract class c {
    private static final String TAG = c.class.getSimpleName();
    private Activity mActivity;
    private sdk.meizu.auth.a mAuthInfo;
    private AuthResponse mAuthResponse;
    private int mNum;
    private sdk.meizu.auth.callback.b mAuthCallbackDelegate;
    private List<Object> mCallObjectList = new ArrayList();

    public c(String str, String str2, String str3, String str4) {
        this.mAuthInfo = new sdk.meizu.auth.a(str, str2, str3, str4);
    }

    public final void requestCodeAuth(Activity activity, String str, CodeCallback codeCallback) {
        Log.v(TAG, "requestCodeAuth");
        requestAuth(activity, b.AUTH_CODE, str, codeCallback);
    }

    public final void requestImplictAuth(Activity activity, String str, ImplictCallback implictCallback) {
        Log.v(TAG, "requestImplictAuth");
        requestAuth(activity, b.IMPLICT, str, implictCallback);
    }

    private final void requestAuth(Activity activity, b bVar, String str, AuthCallback authCallback) {
        if (!ensureAuthCondition(activity, str, authCallback)) {
            return;
        }
        this.mActivity = activity;
        this.mAuthCallbackDelegate = new sdk.meizu.auth.callback.b(authCallback);
        this.mAuthResponse = new AuthResponse(this.mAuthCallbackDelegate);
        if (sdk.meizu.auth.b.a.b(activity)) {
            Log.v(TAG, "requestAuth hasSystemAccount");
            requestSysAuth(activity.getApplicationContext(), bVar, str, this.mAuthCallbackDelegate, 1);
        } else {
            toAuthLogin(activity.getApplicationContext(), bVar, str);
        }
        this.mActivity = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSysAuth(Context context, b bVar, String str, sdk.meizu.auth.callback.b bVar2, int i) {
        this.mNum = i;
        sdk.meizu.auth.b.c cVar = new sdk.meizu.auth.b.c(context, this.mAuthInfo.a(), bVar.a(), str, this.mAuthInfo.b());
        a aVar = new a(this, context, bVar, str, bVar2, this.mNum);
        this.mCallObjectList.add(cVar);
        this.mCallObjectList.add(aVar);
        cVar.a(aVar);
    }

    /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/c$a.class */
    private class a implements sdk.meizu.auth.b.b {
        private SoftReference<c> b;
        private Context c;
        private b d;
        private String e;
        private sdk.meizu.auth.callback.b f;
        private int g;
        private c h;
        private sdk.meizu.auth.callback.a i;

        static /* synthetic */ int a(a aVar) {
            int i = aVar.g;
            aVar.g = i - 1;
            return i;
        }

        public a(c cVar, Context context, b bVar, String str, sdk.meizu.auth.callback.b bVar2, int i) {
            this.b = new SoftReference<>(cVar);
            this.h = this.b.get();
            this.c = context;
            this.d = bVar;
            this.e = str;
            this.f = bVar2;
            this.g = i;
        }

        @Override // sdk.meizu.auth.b.b
        public void a(Intent intent) {
            this.i = new sdk.meizu.auth.callback.a() { // from class: sdk.meizu.auth.c.a.1
                @Override // sdk.meizu.auth.d
                public void a(boolean z) {
                    if (!z) {
                        try {
                            if (a.this.f != null) {
                                a.this.f.a(new OAuthError(OAuthError.CANCEL));
                            }
                            return;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (a.a(a.this) > 0 && a.this.h != null) {
                        a.this.h.requestSysAuth(a.this.c, a.this.d, a.this.e, a.this.f, a.this.g);
                    }
                }
            };
            new AccountLoginResponse(this.i).a(intent);
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        }

        @Override // sdk.meizu.auth.b.b
        public void a() {
            if (this.h != null) {
                this.h.toAuthLogin(this.c, this.d, this.e);
            }
        }

        @Override // sdk.meizu.auth.b.b
        public void a(OAuthToken oAuthToken) {
            try {
                if (this.f != null) {
                    this.f.a(oAuthToken);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(c.TAG, "onGetAuthCode error:" + e.getMessage());
                if (this.h != null) {
                    this.h.toAuthLogin(this.c, this.d, this.e);
                }
            }
        }

        @Override // sdk.meizu.auth.b.b
        public void a(String str) {
            try {
                if (this.f != null) {
                    this.f.a(str);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(c.TAG, "onGetAuthCode error:" + e.getMessage());
                if (this.h != null) {
                    this.h.toAuthLogin(this.c, this.d, this.e);
                }
            }
        }

        @Override // sdk.meizu.auth.b.b
        public void b(String str) {
            if (this.h != null) {
                this.h.toSysAuthLogin(this.c, str, this.d, this.e);
            }
        }

        void b() {
            if (this.b != null) {
                this.b.clear();
                this.b = null;
            }
            this.c = null;
            if (this.f != null) {
                this.f.a();
                this.f = null;
            }
            this.i = null;
        }
    }

    public void toAuthLogin(Context context, b bVar, String str) {
        Log.v(TAG, "toAuthLogin");
        Intent intent = new Intent(context, (Class<?>) AuthActivity.class);
        intent.addFlags(268435456);
        this.mAuthInfo.a(intent, bVar, str);
        this.mAuthResponse.a(intent);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toSysAuthLogin(Context context, String str, b bVar, String str2) {
        Log.v(TAG, "toSysAuthLogin");
        Intent intent = new Intent(context, (Class<?>) AuthActivity.class);
        intent.addFlags(268435456);
        this.mAuthInfo.a(intent, bVar, str2, str);
        this.mAuthResponse.a(intent);
        context.startActivity(intent);
    }

    private boolean ensureAuthCondition(Activity activity, String str, AuthCallback authCallback) {
        if (activity == null) {
            authCallback.onError(new OAuthError(OAuthError.ARGUMENT_ERROR, "activity is null"));
            return false;
        }
        boolean z = true;
        if (!sdk.meizu.auth.c.c.a(activity)) {
            Log.e(TAG, "no available network");
            authCallback.onError(new OAuthError(OAuthError.NETWORK_ERROR, "no available network"));
            z = false;
        }
        if (TextUtils.isEmpty(this.mAuthInfo.a())) {
            Log.e(TAG, "the clientId can't be null!");
            authCallback.onError(new OAuthError(OAuthError.ARGUMENT_ERROR, "the clientId can't be null!"));
            z = false;
        }
        if (TextUtils.isEmpty(this.mAuthInfo.b())) {
            Log.e(TAG, "the redirectUrl can't be null!");
            authCallback.onError(new OAuthError(OAuthError.ARGUMENT_ERROR, "the redirectUrl can't be null!"));
            z = false;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "the scope can't be null!");
            authCallback.onError(new OAuthError(OAuthError.ARGUMENT_ERROR, "the scope can't be null!"));
            z = false;
        }
        return z;
    }

    public void onDestroy() {
        if (this.mCallObjectList != null && this.mCallObjectList.size() > 0) {
            int size = this.mCallObjectList.size();
            for (int i = 0; i < size; i++) {
                Object obj = this.mCallObjectList.get(i);
                if (obj != null) {
                    if (obj instanceof sdk.meizu.auth.b.c) {
                        ((sdk.meizu.auth.b.c) obj).a();
                    } else if (obj instanceof a) {
                        ((a) obj).b();
                    }
                }
            }
            this.mCallObjectList.clear();
        }
        if (this.mAuthCallbackDelegate != null) {
            this.mAuthCallbackDelegate.a();
            this.mAuthCallbackDelegate = null;
        }
        this.mActivity = null;
    }
}
