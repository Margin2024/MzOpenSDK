package sdk.meizu.auth.callback;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import sdk.meizu.auth.d;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/callback/AccountLoginResponse.class */
public class AccountLoginResponse implements Parcelable {
    private d b;
    private static final String a = AccountLoginResponse.class.getSimpleName();
    public static final Parcelable.Creator<AccountLoginResponse> CREATOR = new Parcelable.Creator<AccountLoginResponse>() { // from class: sdk.meizu.auth.callback.AccountLoginResponse.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AccountLoginResponse createFromParcel(Parcel parcel) {
            return new AccountLoginResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AccountLoginResponse[] newArray(int i) {
            return new AccountLoginResponse[i];
        }
    };

    public AccountLoginResponse(d dVar) {
        this.b = dVar;
    }

    private AccountLoginResponse(Parcel parcel) {
        this.b = d.a.a(parcel.readStrongBinder());
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
        intent.putExtra("account_login_response", this);
    }
}
