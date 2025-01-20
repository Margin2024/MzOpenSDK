package sdk.meizu.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/OAuthError.class */
public class OAuthError implements Parcelable {
    private static final String PARAM_ERROR_TYPE = "error_type";
    private static final String PARAM_ERROR_DESCRIPTION = "error_description";
    private String mErrorType;
    private String mErrorDescription;
    public static final Parcelable.Creator<OAuthError> CREATOR = new Parcelable.Creator<OAuthError>() { // from class: sdk.meizu.auth.OAuthError.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public OAuthError createFromParcel(Parcel parcel) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            parcel.readMap(linkedHashMap, OAuthError.class.getClassLoader());
            return new OAuthError(linkedHashMap);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public OAuthError[] newArray(int i) {
            return new OAuthError[i];
        }
    };
    public static final String NETWORK_ERROR = "network_error";
    public static final String ARGUMENT_ERROR = "argument_error";
    public static final String RESPONSE_ERROR = "response_error";
    public static final String CANCEL = "cancel";

    public OAuthError(String str) {
        this(str, "");
    }

    protected OAuthError(HashMap<String, String> hashMap) {
        this(hashMap.get(PARAM_ERROR_TYPE), hashMap.get(PARAM_ERROR_DESCRIPTION));
    }

    public OAuthError(String str, String str2) {
        this.mErrorType = str;
        this.mErrorDescription = str2;
    }

    public String getError() {
        return this.mErrorType;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }

    public static OAuthError fromUri(Uri uri) {
        return new OAuthError(uri.getQueryParameter(PARAM_ERROR_TYPE), uri.getQueryParameter(PARAM_ERROR_DESCRIPTION));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PARAM_ERROR_TYPE, this.mErrorType);
        linkedHashMap.put(PARAM_ERROR_DESCRIPTION, this.mErrorDescription);
        parcel.writeMap(linkedHashMap);
    }
}
