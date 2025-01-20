package sdk.meizu.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/IAuthCallback.class */
public interface IAuthCallback extends IInterface {
    void a(OAuthError oAuthError);

    void a(String str);

    void a(OAuthToken oAuthToken);

    /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/IAuthCallback$Stub.class */
    public static abstract class Stub extends Binder implements IAuthCallback {
        private static final String DESCRIPTOR = "sdk.meizu.auth.IAuthCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onGetCode = 2;
        static final int TRANSACTION_onGetToken = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAuthCallback)) {
                return (IAuthCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            OAuthToken oAuthToken;
            OAuthError oAuthError;
            switch (i) {
                case TRANSACTION_onError /* 1 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (0 != parcel.readInt()) {
                        oAuthError = OAuthError.CREATOR.createFromParcel(parcel);
                    } else {
                        oAuthError = null;
                    }
                    a(oAuthError);
                    parcel2.writeNoException();
                    break;
                case TRANSACTION_onGetCode /* 2 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    a(parcel.readString());
                    parcel2.writeNoException();
                    break;
                case TRANSACTION_onGetToken /* 3 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (0 != parcel.readInt()) {
                        oAuthToken = OAuthToken.CREATOR.createFromParcel(parcel);
                    } else {
                        oAuthToken = null;
                    }
                    a(oAuthToken);
                    parcel2.writeNoException();
                    break;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    break;
            }
            return true;
        }

        /* loaded from: MzOpenSDK1.0.3.aar:classes.jar:sdk/meizu/auth/IAuthCallback$Stub$a.class */
        private static class a implements IAuthCallback {
            private IBinder b;
            public static IAuthCallback a;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // sdk.meizu.auth.IAuthCallback
            public void a(OAuthError oAuthError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (oAuthError != null) {
                        obtain.writeInt(Stub.TRANSACTION_onError);
                        oAuthError.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(Stub.TRANSACTION_onError, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().a(oAuthError);
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

            @Override // sdk.meizu.auth.IAuthCallback
            public void a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.b.transact(Stub.TRANSACTION_onGetCode, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().a(str);
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

            @Override // sdk.meizu.auth.IAuthCallback
            public void a(OAuthToken oAuthToken) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (oAuthToken != null) {
                        obtain.writeInt(Stub.TRANSACTION_onError);
                        oAuthToken.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(Stub.TRANSACTION_onGetToken, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().a(oAuthToken);
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

        public static boolean setDefaultImpl(IAuthCallback iAuthCallback) {
            if (a.a != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAuthCallback != null) {
                a.a = iAuthCallback;
                return true;
            }
            return false;
        }

        public static IAuthCallback getDefaultImpl() {
            return a.a;
        }
    }
}
