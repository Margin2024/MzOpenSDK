package sdk.meizu.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/OAuthToken.class */
public class OAuthToken implements Parcelable {
    public static final String PARAM_OPEN_ID = "open_id";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_TOKEN_TYPE = "token_type";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    private String mAccessToken;
    private String mTokenType;
    private String mExpireIn;
    private String mOpenId;
    public static final Parcelable.Creator<OAuthToken> CREATOR = new Parcelable.Creator<OAuthToken>() { // from class: sdk.meizu.auth.OAuthToken.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public OAuthToken createFromParcel(Parcel parcel) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            parcel.readMap(linkedHashMap, OAuthToken.class.getClassLoader());
            return new OAuthToken(linkedHashMap);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public OAuthToken[] newArray(int i) {
            return new OAuthToken[i];
        }
    };

    private OAuthToken(HashMap<String, String> hashMap) {
        this.mAccessToken = hashMap.get(PARAM_ACCESS_TOKEN);
        if (TextUtils.isEmpty(this.mAccessToken)) {
            throw new IllegalArgumentException("access_token is null");
        }
        this.mTokenType = hashMap.get(PARAM_TOKEN_TYPE);
        this.mExpireIn = hashMap.get(PARAM_EXPIRES_IN);
        this.mOpenId = hashMap.get(PARAM_OPEN_ID);
    }

    public String getExpireIn() {
        return this.mExpireIn;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public String getOpenId() {
        return this.mOpenId;
    }

    public static OAuthToken fromDataMap(HashMap<String, String> hashMap) {
        return new OAuthToken(hashMap);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PARAM_ACCESS_TOKEN, this.mAccessToken);
        linkedHashMap.put(PARAM_TOKEN_TYPE, this.mTokenType);
        linkedHashMap.put(PARAM_EXPIRES_IN, this.mExpireIn);
        linkedHashMap.put(PARAM_OPEN_ID, this.mOpenId);
        parcel.writeMap(linkedHashMap);
    }
}
