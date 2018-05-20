package forallstudio.mobilephone.detail;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.data.source.IMobileRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MobileDetailPresenter implements IMobileDetailPresenter.Action {

    private IMobileRepository repository;
    private MobileDetailViewModel viewModel;

    public MobileDetailPresenter(IMobileRepository repository, MobileDetailViewModel viewModel) {
        this.repository = repository;
        this.viewModel = viewModel;
    }

    @Override
    public void getMobileDetailById(int mobileId) {
        Mobile mobile = repository.getMobileById(mobileId);
        viewModel.setMobile(mobile);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMobileImages(int mobileId) {
        repository.getMobileImages(mobileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(errorHandler())
                .subscribe(this::updateViewModel);
    }

    @NonNull
    private Consumer<Throwable> errorHandler() {
        return throwable -> Log.d("---->", "Throwable : " + throwable.toString());
    }

    private void updateViewModel(List<MobileImage> mobileImages) {
        viewModel.setMobileImages(mobileImages);
    }

}