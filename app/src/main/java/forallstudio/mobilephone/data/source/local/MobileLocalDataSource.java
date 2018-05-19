package forallstudio.mobilephone.data.source.local;

import forallstudio.mobilephone.data.Mobile;
import io.reactivex.Completable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MobileLocalDataSource implements IMobileLocalDataSource {

    private static volatile IMobileLocalDataSource localDataStore;
    private Realm realm;

    public static IMobileLocalDataSource getInstance() {
        if(localDataStore == null) {
            synchronized (MobileLocalDataSource.class) {
                if(localDataStore == null) {
                    localDataStore = new MobileLocalDataSource();
                }
            }
        }
        return localDataStore;
    }

    private MobileLocalDataSource() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public RealmResults<Mobile> getAllMobileList() {
        return realm.where(Mobile.class).findAll();
    }

    @Override
    public RealmResults<Mobile> getAllMobileList(String fieldName, Sort sort) {
        return getAllMobileList().sort(fieldName, sort);
    }

    @Override
    public RealmResults<Mobile> getAllMobileFavorite() {
        return getAllMobileList().where()
                .equalTo("isFavorite",true)
                .findAll();
    }

    @Override
    public RealmResults<Mobile> getAllMobileFavorite(String fieldName, Sort sort) {
        return getAllMobileFavorite().sort(fieldName, sort);
    }

    @Override
    public Mobile getMobileById(int mobileId) {
        return realm.where(Mobile.class)
                .equalTo("id", mobileId)
                .findFirst();
    }

    @Override
    public Completable saveOrUpdateMobile(Mobile mobile) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mobile);
        realm.commitTransaction();
        return Completable.complete();
    }

    @Override
    public Completable deleteMobileFavorite(int mobileId) {
        Mobile mobile = getMobileById(mobileId);
        realm.beginTransaction();
        mobile.setFavorite(false);
        realm.commitTransaction();
        return Completable.complete();
    }

}