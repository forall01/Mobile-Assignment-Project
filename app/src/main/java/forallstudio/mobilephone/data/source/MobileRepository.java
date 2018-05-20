package forallstudio.mobilephone.data.source;

import java.util.Collections;
import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.data.source.local.IMobileLocalDataSource;
import forallstudio.mobilephone.data.source.remote.IMobileRemoteDataSource;
import forallstudio.mobilephone.utils.Utils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.realm.RealmResults;

public class MobileRepository implements IMobileRepository {

    private static IMobileRepository repository;

    private IMobileLocalDataSource localDataSource;
    private IMobileRemoteDataSource remoteDataSource;

    public static IMobileRepository getInstance(IMobileLocalDataSource localDataSource,
                                                IMobileRemoteDataSource remoteDataSource) {
        if (repository == null) {
            repository = new MobileRepository(localDataSource, remoteDataSource);
        }
        return repository;
    }

    private MobileRepository(IMobileLocalDataSource localDataSource,
                             IMobileRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Flowable<RealmResults<Mobile>> getAllMobileList(MobileSortType sort) {
        return remoteDataSource.getAllMobileList()
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> Collections.emptyList()) // if error then return empty list
                .doOnNext(this::saveMobileToDataBase)
                .flatMap((Function<List<Mobile>, Flowable<RealmResults<Mobile>>>) mobiles ->
                        Flowable.fromCallable(() ->
                                localDataSource.getAllMobileList(sort.getFieldName(), sort.getSort())
                        )
                );
    }

    @Override
    public Flowable<RealmResults<Mobile>> getAllMobileFavoriteList(MobileSortType sort) {
        return localDataSource
                .getAllMobileFavorite(sort.getFieldName(), sort.getSort()).asFlowable();
    }

    @Override
    public Flowable<List<MobileImage>> getMobileImages(int mobileId) {
        return remoteDataSource.getMobileImages(mobileId)
                .onErrorReturn(throwable -> Collections.emptyList()) // if error then return empty list
                .map(mobileImages -> {
                    for (MobileImage mobileImage : mobileImages) {
                        String url = Utils.convertUrlIfMissingHttpProtocol(mobileImage.getUrl());
                        mobileImage.setUrl(url);
                    }
                    return mobileImages;
                });
    }

    @Override
    public Flowable<RealmResults<Mobile>> sortAllMobileList(MobileSortType sort) {
        return localDataSource.getAllMobileList(sort.getFieldName(), sort.getSort()).asFlowable();
    }

    @Override
    public Mobile getMobileById(int mobileId) {
        return localDataSource.getMobileById(mobileId);
    }

    @Override
    public Completable saveOrUpdateMobile(Mobile mobile) {
        return localDataSource.saveOrUpdateMobile(mobile);
    }

    @Override
    public Completable deleteMobileFavorite(int mobileId) {
        return localDataSource.deleteMobileFavorite(mobileId);
    }

    private void saveMobileToDataBase(List<Mobile> mobiles) {
        for (Mobile mobile : mobiles) {
            saveOrUpdateMobile(mobile);
        }
    }

}