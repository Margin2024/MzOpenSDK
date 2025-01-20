package sdk.meizu.auth;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/a.class */
public class a {
    private String a;
    private String b;
    private String c;
    private b d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    private a(String str, String str2, String str3, String str4, b bVar, String str5, String str6) {
        this.a = str3;
        this.b = str4;
        this.d = bVar;
        this.c = str5;
        this.e = str6;
        this.h = str;
        this.i = str2;
    }

    public a(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null, "", "");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public b c() {
        return this.d;
    }

    public boolean d() {
        return !TextUtils.isEmpty(this.e);
    }

    public String e() {
        if (this.f == null) {
            this.f = Uri.parse(this.h).buildUpon().appendQueryParameter("response_type", this.d.a()).appendQueryParameter("redirect_uri", this.b).appendQueryParameter("scope", this.c).appendQueryParameter("client_id", this.a).build().toString();
        }
        return this.f;
    }

    public String f() {
        if (this.g == null) {
            this.g = Uri.parse(this.i).buildUpon().appendQueryParameter("autoLoginCode", this.e).appendQueryParameter("redirect_url", e()).build().toString();
        }
        return this.g;
    }

    public void a(Intent intent, b bVar, String str) {
        this.c = str;
        intent.putExtra("auth_url", this.h);
        intent.putExtra("sys_auth_url", this.i);
        intent.putExtra("client_id", this.a);
        intent.putExtra("redirect_uri", this.b);
        intent.putExtra("scope", this.c);
        bVar.a(intent);
    }

    public void a(Intent intent, b bVar, String str, String str2) {
        a(intent, bVar, str);
        intent.putExtra("autoLoginCode", str2);
    }

    public static a a(Intent intent) {
        return new a(intent.getStringExtra("auth_url"), intent.getStringExtra("sys_auth_url"), intent.getStringExtra("client_id"), intent.getStringExtra("redirect_uri"), b.b(intent), intent.getStringExtra("scope"), intent.getStringExtra("autoLoginCode"));
    }
}
