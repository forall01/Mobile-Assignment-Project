package forallstudio.mobilephone.data.source.remote.base;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface IRemoteClient {

    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();
    Interceptor getInterceptor();
    <T> T createApi(Class<T> service);

}