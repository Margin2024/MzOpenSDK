package sdk.meizu.auth.c;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/c/d.class */
public class d {
    public static final d a = new d("10.0.0.172", 80);
    private static d b = null;
    private String c;
    private int d;

    public static final void a(String str) {
        if (b(str)) {
            b = a;
        } else {
            b = null;
        }
    }

    private d(String str, int i) {
        this.c = null;
        this.d = 0;
        this.c = str;
        this.d = i;
    }

    private static final boolean b(String str) {
        if (str != null && str.toUpperCase().contains("CMWAP")) {
            return true;
        }
        return false;
    }
}
