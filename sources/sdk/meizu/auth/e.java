package sdk.meizu.auth;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/e.class */
public class e {
    private static final String a = e.class.getSimpleName();
    private OAuthToken b;
    private OAuthError c;

    public e(String str) {
        int indexOf;
        String str2 = null;
        if (str != null && (indexOf = str.indexOf(35) + 1) > 0 && indexOf < str.length()) {
            str2 = str.substring(indexOf);
        }
        if (!TextUtils.isEmpty(str2)) {
            if (str2.contains(OAuthToken.PARAM_ACCESS_TOKEN)) {
                try {
                    this.b = OAuthToken.fromDataMap(a(str2));
                    return;
                } catch (Exception e) {
                    Log.e(a, "ImplictAuthResponse parse:" + e.getMessage());
                    this.c = new OAuthError(OAuthError.RESPONSE_ERROR, e.getMessage());
                    return;
                }
            }
            return;
        }
        this.c = OAuthError.fromUri(Uri.parse(str));
    }

    private HashMap<String, String> a(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=");
            linkedHashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
        }
        return linkedHashMap;
    }

    public OAuthToken a() {
        return this.b;
    }

    public OAuthError b() {
        return this.c;
    }

    public boolean c() {
        return this.b != null;
    }
}
