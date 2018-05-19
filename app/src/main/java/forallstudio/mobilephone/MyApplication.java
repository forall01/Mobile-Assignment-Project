package forallstudio.mobilephone;

import android.app.Application;

import forallstudio.mobilephone.data.source.local.RealmMobile;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmMobile.init(this);
    }

}