package sdk.meizu.auth.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/c/c.class */
public class c {
    private static final String a = c.class.getSimpleName();

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        boolean z = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                d.a(activeNetworkInfo.getExtraInfo());
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(a, "The android.permission.ACCESS_NETWORK_STATE needed!");
            z = true;
        }
        return z;
    }
}
