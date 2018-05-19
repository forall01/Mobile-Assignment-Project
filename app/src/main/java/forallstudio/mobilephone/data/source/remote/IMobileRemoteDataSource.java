package forallstudio.mobilephone.data.source.remote;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import io.reactivex.Flowable;

public interface IMobileRemoteDataSource {

    Flowable<List<Mobile>> getAllMobileList();

    Flowable<List<MobileImage>> getMobileImages(int mobileId);

}