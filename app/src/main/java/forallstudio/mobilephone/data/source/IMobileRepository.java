package forallstudio.mobilephone.data.source;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.realm.RealmResults;

public interface IMobileRepository {

    Flowable<RealmResults<Mobile>> getAllMobileList(MobileSortType sort);

    Flowable<RealmResults<Mobile>> getAllMobileFavoriteList(MobileSortType sort);

    Flowable<List<MobileImage>> getMobileImages(int mobileId);

    Flowable<RealmResults<Mobile>> sortAllMobileList(MobileSortType sort);

    Mobile getMobileById(int mobileId);

    Completable saveOrUpdateMobile(Mobile mobile);

    Completable deleteMobileFavorite(int mobileId);

}