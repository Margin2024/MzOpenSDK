package sdk.meizu.auth.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;
import sdk.meizu.auth.OAuthError;
import sdk.meizu.auth.a;
import sdk.meizu.auth.c.b;
import sdk.meizu.auth.callback.AuthResponse;
import sdk.meizu.auth.e;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/ui/AuthActivity.class */
public class AuthActivity extends Activity {
    private static final String b = AuthActivity.class.getSimpleName();
    protected WebView a;
    private AuthResponse c;
    private a d;
    private boolean e = false;
    private FrameLayout f;
    private boolean g;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        a();
        d();
        b();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && i == 4) {
            if (this.a.canGoBack()) {
                this.a.goBack();
                return true;
            }
            a(new OAuthError(OAuthError.CANCEL));
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void a() {
        Log.v(b, "initWebView");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.f = new FrameLayout(this);
        this.f.setLayoutParams(layoutParams);
        this.f.setFitsSystemWindows(true);
        this.a = new WebView(getApplicationContext());
        this.a.setLayoutParams(layoutParams);
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                this.a.removeJavascriptInterface("searchBoxJavaBridge_");
            }
            this.a.removeJavascriptInterface("accessibility");
            this.a.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f.addView(this.a);
        setContentView(this.f);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setAppCacheEnabled(false);
        settings.setSavePassword(false);
        try {
            settings.setAllowFileAccess(false);
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.a.setWebViewClient(new MyWebViewClient(this));
    }

    /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/ui/AuthActivity$MyWebViewClient.class */
    private class MyWebViewClient extends WebViewClient {
        private WeakReference<AuthActivity> mWeakReference;

        public MyWebViewClient(AuthActivity authActivity) {
            this.mWeakReference = new WeakReference<>(authActivity);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AuthActivity authActivity = this.mWeakReference.get();
            if (authActivity != null && authActivity.d != null && str.startsWith(authActivity.d.b())) {
                switch (authActivity.d.c()) {
                    case AUTH_CODE:
                        authActivity.a(str);
                        break;
                    case IMPLICT:
                        authActivity.b(str);
                        break;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.v(AuthActivity.b, "onPageStarted");
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            AuthActivity.this.a(sslErrorHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final SslErrorHandler sslErrorHandler) {
        if (!this.g && !sdk.meizu.auth.c.a.a) {
            this.g = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The site's security certificate has expired or is unavailable.");
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() { // from class: sdk.meizu.auth.ui.AuthActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    sslErrorHandler.proceed();
                    sdk.meizu.auth.c.a.a = true;
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: sdk.meizu.auth.ui.AuthActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
        }
    }

    private void d() {
        Log.v(b, "parseIntent");
        Intent intent = getIntent();
        this.c = AuthResponse.b(intent);
        this.d = a.a(intent);
    }

    protected void b() {
        Log.v(b, "loadAuthPage isSysAuth : " + this.d.d());
        b.a(this);
        if (this.d.d()) {
            this.a.loadUrl(this.d.f());
        } else {
            this.a.loadUrl(this.d.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Log.v(b, "handleCodeResponse");
        if (!this.e) {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("code");
            if (!TextUtils.isEmpty(queryParameter)) {
                this.e = true;
                if (this.c != null) {
                    this.c.a(queryParameter);
                }
                finish();
                return;
            }
            a(OAuthError.fromUri(parse));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Log.v(b, "handleImplictResponse");
        if (!this.e) {
            OAuthError oAuthError = null;
            e eVar = new e(str);
            if (eVar.c()) {
                this.e = true;
                if (this.c != null) {
                    this.c.a(eVar.a());
                }
                finish();
            } else {
                oAuthError = eVar.b();
            }
            if (oAuthError != null) {
                a(oAuthError);
            }
        }
    }

    private void a(OAuthError oAuthError) {
        Log.v(b, "handleAuthError");
        this.e = true;
        if (this.c != null) {
            this.c.a(oAuthError);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.a != null) {
            ViewParent parent = this.a.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.a);
            }
            this.a.setWebViewClient(null);
            this.a.setOnLongClickListener(null);
            this.a.setWebChromeClient(null);
            this.a.stopLoading();
            this.a.clearHistory();
            this.a.removeAllViews();
            this.a.getSettings().setJavaScriptEnabled(false);
            try {
                this.a.destroy();
            } catch (Exception e) {
            }
        }
        this.c = null;
        this.d = null;
        this.f.removeAllViews();
        this.f = null;
        super.onDestroy();
        System.gc();
    }
}
