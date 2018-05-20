package forallstudio.mobilephone.data.source.local;

import forallstudio.mobilephone.data.Mobile;
import io.reactivex.Completable;
import io.realm.RealmResults;
import io.realm.Sort;

public interface IMobileLocalDataSource {

    RealmResults<Mobile> getAllMobileList();

    RealmResults<Mobile> getAllMobileList(String fieldName, Sort sort);

    RealmResults<Mobile> getAllMobileFavorite();

    RealmResults<Mobile> getAllMobileFavorite(String fieldName, Sort sort);

    Mobile getMobileById(int mobileId);

    Completable saveOrUpdateMobile(Mobile mobile);

    Completable deleteMobileFavorite(int mobileId);

}