package sdk.meizu.auth.c;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/c/b.class */
public class b {
    public static void a(Context context) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();
            cookieManager.removeAllCookie();
            CookieSyncManager.getInstance().sync();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
