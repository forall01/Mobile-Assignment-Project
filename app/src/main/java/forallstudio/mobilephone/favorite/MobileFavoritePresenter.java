package forallstudio.mobilephone.favorite;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.IMobileRepository;
import forallstudio.mobilephone.data.source.MobileSortType;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.realm.Realm;
import io.realm.RealmResults;

public class MobileFavoritePresenter implements IMobileFavoritePresenter.Action {

    private IMobileRepository repository;
    private MobileFavoriteViewModel viewModel;
    private IMobileFavoritePresenter.View view;

    private RealmResults<Mobile> favoriteRealmResult;

    public MobileFavoritePresenter(IMobileRepository repository,
                                   MobileFavoriteViewModel viewModel,
                                   IMobileFavoritePresenter.View view) {
        this.repository = repository;
        this.viewModel = viewModel;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMobileFavorite(MobileSortType sort) {
        repository.getAllMobileFavoriteList(sort)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(errorHandler())
                .subscribe(updateViewAndObserveDataChange());
    }

    @Override
    public void onFavoriteClicked(Mobile mobile) {
        view.openMobileDetailScreen(mobile.getId());
    }

    @Override
    public void onSwipeToDelete(int mobileId) {
        repository.deleteMobileFavorite(mobileId);
    }

    @NonNull
    private Consumer<Throwable> errorHandler() {
        return throwable -> Log.d("---->", "Throwable : " + throwable.toString());
    }

    @NonNull
    private Consumer<RealmResults<Mobile>> updateViewAndObserveDataChange() {
        return mobiles -> {
            updateViewModel(mobiles);
            observeDataChanged(mobiles);
        };
    }

    private void observeDataChanged(RealmResults<Mobile> mobileRealmResults) {
        this.favoriteRealmResult = mobileRealmResults;
        this.favoriteRealmResult.removeAllChangeListeners();
        this.favoriteRealmResult.addChangeListener((mobilesChanged, changeSet) ->
                updateViewModel(mobilesChanged)
        );
    }

    private void updateViewModel(RealmResults<Mobile> mobiles) {
        viewModel.setMobiles(copyFromRealm(mobiles));
    }

    private List<Mobile> copyFromRealm(RealmResults<Mobile> mobiles) {
        return Realm.getDefaultInstance().copyFromRealm(mobiles);
    }

}