package forallstudio.mobilephone.data.source.remote;

import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.data.source.remote.base.RemoteClient;
import io.reactivex.Flowable;

class MobileRemoteDataSource implements IMobileRemoteDataSource {

    private static IMobileRemoteDataSource remoteDataSource;

    private IMobileApi api;

    public static IMobileRemoteDataSource getInstance() {
        if(remoteDataSource == null) {
            remoteDataSource = new MobileRemoteDataSource();
        }
        return remoteDataSource;
    }

    private MobileRemoteDataSource() {
        api = RemoteClient.build().createApi(IMobileApi.class);
    }

    @Override
    public Flowable<List<Mobile>> getAllMobileList() {
        return api.getAllMobileList();
    }

    @Override
    public Flowable<List<MobileImage>> getMobileImages(int mobileId) {
        return api.getMobileImages(mobileId);
    }

}