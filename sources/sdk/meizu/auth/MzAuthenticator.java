package sdk.meizu.auth;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/MzAuthenticator.class */
public final class MzAuthenticator extends c {
    private static final String BASE_AUTH_URL = "https://open-api.flyme.cn/oauth/authorize";
    private static final String SYS_AUTH_URL = "https://open-api.flyme.cn/oauth/autoLoginByCode.do";
    private static final String TAG = MzAuthenticator.class.getSimpleName();

    public MzAuthenticator(String str, String str2) {
        super(BASE_AUTH_URL, SYS_AUTH_URL, str, str2);
    }
}
