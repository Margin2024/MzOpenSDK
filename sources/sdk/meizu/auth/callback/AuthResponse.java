package sdk.meizu.auth.callback;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import sdk.meizu.auth.IAuthCallback;
import sdk.meizu.auth.OAuthError;
import sdk.meizu.auth.OAuthToken;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/callback/AuthResponse.class */
public class AuthResponse implements Parcelable {
    private IAuthCallback b;
    private static final String a = AuthResponse.class.getSimpleName();
    public static final Parcelable.Creator<AuthResponse> CREATOR = new Parcelable.Creator<AuthResponse>() { // from class: sdk.meizu.auth.callback.AuthResponse.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthResponse createFromParcel(Parcel parcel) {
            return new AuthResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthResponse[] newArray(int i) {
            return new AuthResponse[i];
        }
    };

    public AuthResponse(IAuthCallback iAuthCallback) {
        this.b = iAuthCallback;
    }

    private AuthResponse(Parcel parcel) {
        this.b = IAuthCallback.Stub.asInterface(parcel.readStrongBinder());
    }

    public void a(String str) {
        try {
            this.b.a(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void a(OAuthToken oAuthToken) {
        try {
            this.b.a(oAuthToken);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void a(OAuthError oAuthError) {
        try {
            this.b.a(oAuthError);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.b.asBinder());
    }

    public void a(Intent intent) {
        intent.putExtra("auth_response", this);
    }

    public static AuthResponse b(Intent intent) {
        return (AuthResponse) intent.getParcelableExtra("auth_response");
    }
}
