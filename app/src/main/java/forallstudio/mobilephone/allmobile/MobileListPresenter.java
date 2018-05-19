package forallstudio.mobilephone.allmobile;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.IMobileRepository;
import forallstudio.mobilephone.data.source.MobileSortType;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class MobileListPresenter implements IMobileListPresenter.Action {

    private IMobileListPresenter.View view;
    private IMobileRepository repository;
    private MobileListViewModel viewModel;

    private RealmResults<Mobile> mobileResults;

    public MobileListPresenter(IMobileRepository repository,
                               MobileListViewModel viewModel,
                               IMobileListPresenter.View view) {
        this.repository = repository;
        this.viewModel = viewModel;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMobileList(MobileSortType sort) {
        repository.getAllMobileList(sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(errorHandler())
                .subscribe(updateViewModelAndObserveDataChange());
    }

    @SuppressLint("CheckResult")
    @Override
    public void sortAllMobileList(MobileSortType sort) {
        repository.sortAllMobileList(sort)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(errorHandler())
                .subscribe(updateViewModelAndObserveDataChange());
    }

    @Override
    public void onMobileClicked(Mobile mobile) {
        view.openMobileDetailScreen(mobile.getId());
    }

    @Override
    public void onFavoriteClicked(Mobile mobile) {
        mobile.setFavorite(!mobile.isFavorite());
        repository.saveOrUpdateMobile(mobile);
    }

    @NonNull
    private Consumer<Throwable> errorHandler() {
        return throwable -> Log.d("---->", "Throwable : " + throwable.toString());
    }

    @NonNull
    private Consumer<RealmResults<Mobile>> updateViewModelAndObserveDataChange() {
        return mobiles -> {
            updateViewModel(mobiles);
            observeDataChanged(mobiles);
        };
    }

    private void observeDataChanged(RealmResults<Mobile> mobileResults) {
        this.mobileResults = mobileResults;
        this.mobileResults.removeAllChangeListeners();
        this.mobileResults.addChangeListener((mobilesChanged, changeSet) ->
                updateViewModel(mobilesChanged)
        );
    }

    private void updateViewModel(RealmResults<Mobile> mobileResults) {
        viewModel.setMobiles(copyFromRealm(mobileResults));
    }

    private List<Mobile> copyFromRealm(RealmResults<Mobile> mobiles) {
        return Realm.getDefaultInstance().copyFromRealm(mobiles);
    }

}