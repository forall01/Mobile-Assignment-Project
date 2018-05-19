package forallstudio.mobilephone.data.source.remote;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IMobileApi {

    @GET("api/mobiles/")
    Flowable<List<Mobile>> getAllMobileList();

    @GET("api/mobiles/{mobileId}/images")
    Flowable<List<MobileImage>> getMobileImages(@Path("mobileId") int mobileId);

}