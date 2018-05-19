package forallstudio.mobilephone.data.source.local;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmMobile {

    private RealmMobile(){}

    public static void init(Context context) {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .modules(new RealmMobileModule())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}