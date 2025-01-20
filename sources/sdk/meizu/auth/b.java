package sdk.meizu.auth;

import android.content.Intent;
import android.util.Log;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/b.class */
public enum b {
    AUTH_CODE("code"),
    IMPLICT("token");

    String c;
    private static final String d = b.class.getSimpleName();

    b(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public void a(Intent intent) {
        intent.putExtra("auth_type", name());
    }

    public static b b(Intent intent) {
        b bVar = null;
        try {
            bVar = valueOf(intent.getStringExtra("auth_type"));
        } catch (IllegalArgumentException e2) {
            Log.e(d, e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            Log.e(d, e3.getMessage());
            e3.printStackTrace();
        }
        if (bVar != null) {
            return bVar;
        }
        return AUTH_CODE;
    }
}
