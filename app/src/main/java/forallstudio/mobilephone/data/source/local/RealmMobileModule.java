package forallstudio.mobilephone.data.source.local;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import io.realm.annotations.RealmModule;

@RealmModule(classes = {
        Mobile.class,
        MobileImage.class
})
public class RealmMobileModule {
}