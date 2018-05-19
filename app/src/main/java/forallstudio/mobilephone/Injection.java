package forallstudio.mobilephone;

import forallstudio.mobilephone.data.source.IMobileRepository;
import forallstudio.mobilephone.data.source.MobileRepository;
import forallstudio.mobilephone.data.source.local.MobileLocalDataSource;
import forallstudio.mobilephone.data.source.remote.MobileRemoteDataSource;

public class Injection {

    public static IMobileRepository provideMobileRepository() {
        return MobileRepository.getInstance(
                MobileLocalDataSource.getInstance(),
                MobileRemoteDataSource.getInstance()
        );
    }

}