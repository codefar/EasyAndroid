package org.greenleaf.easyandroid.di.module;

import android.support.annotation.NonNull;

import org.greenleaf.easyandroid.api.GankIoService;
import org.greenleaf.easyandroid.api.NeteasApiService;
import org.greenleaf.easyandroid.api.ServerService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/8/25
 * time: 14:02
 */
@Module
public class NetModule {
    /**
     * 网络请求Service创建
     *
     * @param host        域名
     * @param cls         service的class
     * @param <T>         泛型
     * @return T
     */
    private static <T> T createService(String host, Class<T> cls) {
        OkHttpClient.Builder builder = buildOkHttpClientBuilder();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create()).
                baseUrl(host).
                client(builder.build());
        return retrofitBuilder.build().create(cls);
    }

    @NonNull
    private static OkHttpClient.Builder buildOkHttpClientBuilder() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                connectTimeout(15, TimeUnit.SECONDS).
                readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging);
        return builder;
    }

    @Singleton
    @Provides
    public ServerService provideServerService() {
        return createService("https://image.baidu.com/", ServerService.class);
    }

    @Singleton
    @Provides
    public GankIoService provideGankIoService() {
        return createService("http://gank.io/api/", GankIoService.class);
    }

    @Singleton
    @Provides
    public NeteasApiService provideNeteasApiService() {
        return createService("https://image.baidu.com/", NeteasApiService.class);
    }
}
