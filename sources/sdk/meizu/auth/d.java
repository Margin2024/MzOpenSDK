package sdk.meizu.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/d.class */
public interface d extends IInterface {
    void a(boolean z);

    /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/d$a.class */
    public static abstract class a extends Binder implements d {
        public a() {
            attachInterface(this, "sdk.meizu.auth.IAccountLoginCallback");
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("sdk.meizu.auth.IAccountLoginCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof d)) {
                return (d) queryLocalInterface;
            }
            return new C0000a(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    parcel.enforceInterface("sdk.meizu.auth.IAccountLoginCallback");
                    a(0 != parcel.readInt());
                    parcel2.writeNoException();
                    break;
                case 1598968902:
                    parcel2.writeString("sdk.meizu.auth.IAccountLoginCallback");
                    break;
            }
            return true;
        }

        /* renamed from: sdk.meizu.auth.d$a$a, reason: collision with other inner class name */
        /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/d$a$a.class */
        private static class C0000a implements d {
            private IBinder b;
            public static d a;

            C0000a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // sdk.meizu.auth.d
            public void a(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("sdk.meizu.auth.IAccountLoginCallback");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.b.transact(1, obtain, obtain2, 0) && a.a() != null) {
                        a.a().a(z);
                        obtain2.recycle();
                        obtain.recycle();
                    } else {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                    }
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }
        }

        public static d a() {
            return C0000a.a;
        }
    }
}
