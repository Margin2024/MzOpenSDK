package sdk.meizu.auth.b;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.util.HashMap;
import sdk.meizu.auth.OAuthToken;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/b/c.class */
public class c {
    private static final String a = c.class.getSimpleName();
    private Context b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private String g;
    private AccountManagerFuture<Bundle> h;

    public c(Context context, String str, String str2, String str3, String str4) {
        this.b = context;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
    }

    public void a(final b bVar) {
        AccountManager accountManager = AccountManager.get(this.b);
        Account a2 = a.a(this.b);
        if (a2 == null) {
            a2 = new Account("unknown", "com.meizu.account");
        }
        Bundle bundle = new Bundle();
        bundle.putString("client_id", this.d);
        bundle.putString("auth_type", this.e);
        bundle.putString("scope", this.f);
        bundle.putString("redirect_url", this.g);
        this.c = false;
        this.h = accountManager.getAuthToken(a2, "authTrustToken", bundle, (Activity) null, new AccountManagerCallback<Bundle>() { // from class: sdk.meizu.auth.b.c.1
            @Override // android.accounts.AccountManagerCallback
            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                Log.d(c.a, "receive account callback");
                if (c.this.c) {
                    Log.d(c.a, "op canceled.");
                    return;
                }
                try {
                    Bundle result = accountManagerFuture.getResult();
                    if (result == null) {
                        c.this.b(bVar);
                    } else if (result.containsKey("intent")) {
                        c.this.a((Intent) result.getParcelable("intent"), bVar);
                    } else if (result.containsKey(OAuthToken.PARAM_ACCESS_TOKEN)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(OAuthToken.PARAM_ACCESS_TOKEN, result.getString(OAuthToken.PARAM_ACCESS_TOKEN));
                        hashMap.put(OAuthToken.PARAM_TOKEN_TYPE, result.getString(OAuthToken.PARAM_TOKEN_TYPE));
                        hashMap.put(OAuthToken.PARAM_EXPIRES_IN, result.getString(OAuthToken.PARAM_EXPIRES_IN));
                        hashMap.put(OAuthToken.PARAM_OPEN_ID, result.getString(OAuthToken.PARAM_OPEN_ID));
                        c.this.a(OAuthToken.fromDataMap(hashMap), bVar);
                    } else if (result.containsKey("code")) {
                        c.this.b(result.getString("code"), bVar);
                    } else if (!result.containsKey("auto_login_code")) {
                        c.this.b(bVar);
                    } else {
                        c.this.a(result.getString("auto_login_code"), bVar);
                    }
                } catch (OperationCanceledException e) {
                } catch (Exception e2) {
                    c.this.b(bVar);
                }
            }
        }, (Handler) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, b bVar) {
        if (bVar != null && !this.c) {
            bVar.a(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, b bVar) {
        if (bVar != null && !this.c) {
            bVar.b(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OAuthToken oAuthToken, b bVar) {
        if (bVar != null && !this.c) {
            bVar.a(oAuthToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, b bVar) {
        if (bVar != null && !this.c) {
            bVar.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(b bVar) {
        if (bVar != null && !this.c) {
            bVar.a();
        }
    }

    public void a() {
        try {
            this.h.cancel(true);
        } catch (Exception e) {
        }
        this.c = true;
    }
}
