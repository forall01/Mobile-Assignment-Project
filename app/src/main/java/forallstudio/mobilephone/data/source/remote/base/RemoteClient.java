package forallstudio.mobilephone.data.source.remote.base;

import java.util.concurrent.TimeUnit;

import forallstudio.mobilephone.BuildConfig;
import forallstudio.mobilephone.data.source.remote.MobileApiEndPoint;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteClient implements IRemoteClient {

    private static final Long TIMEOUT_CONNECTION = 5L;
    private static final Long TIMEOUT_READ = 5L;
    private static final Long TIMEOUT_WRITE = 5L;

    public static RemoteClient build() {
        return new RemoteClient();
    }

    @Override
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MobileApiEndPoint.END_POINT)
                .addConverterFactory(getConverter())
                .addCallAdapterFactory(getCallAdapter())
                .client(getOkHttpClient())
                .build();
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.MINUTES)
                .readTimeout(TIMEOUT_READ, TimeUnit.MINUTES)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.MINUTES)
                .addInterceptor(getInterceptor());

        if (BuildConfig.DEBUG) {
            client.addInterceptor(getHttpLoggingInterceptor());
        }

        return client.build();
    }

    @Override
    public Interceptor getInterceptor() {
        return null;
    }

    @Override
    public <T> T createApi(Class<T> service) {
        return getRetrofit().create(service);
    }

    public Converter.Factory getConverter() {
        return GsonConverterFactory.create();
    }

    public CallAdapter.Factory getCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    // TODO : SHOULD BE REMOVE IN PRODUCTION
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

}