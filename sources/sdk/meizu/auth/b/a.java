package sdk.meizu.auth.b;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/b/a.class */
public class a {
    private static final String a = a.class.getSimpleName();

    public static Account a(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.meizu.account");
        if (accountsByType == null || accountsByType.length == 0) {
            return null;
        }
        if (accountsByType.length != 1) {
            Log.e(a, "more than 1 flyme account : " + accountsByType.length);
        }
        return accountsByType[0];
    }

    public static boolean b(Context context) {
        boolean z = false;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo("com.meizu.account", 128).metaData;
            if (bundle != null) {
                z = bundle.getBoolean("com.meizu.account.SUPPORT_QUICK_AUTH", false);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(a, "supportAutoLogin error:" + e.getMessage());
            e.printStackTrace();
        }
        return z;
    }
}
