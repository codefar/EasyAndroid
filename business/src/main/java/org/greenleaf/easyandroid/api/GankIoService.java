package org.greenleaf.easyandroid.api;

import org.greenleaf.easyandroid.bean.All;
import org.greenleaf.easyandroid.bean.Android;
import org.greenleaf.easyandroid.bean.Extent;
import org.greenleaf.easyandroid.bean.FrontEnd;
import org.greenleaf.easyandroid.bean.Fuli;
import org.greenleaf.easyandroid.bean.Ios;
import org.greenleaf.easyandroid.bean.Video;
import org.greenleaf.easyandroid.core.utils.ApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 请求个数： 数字，大于0
 第几页：数字，大于0
 例：
 http://gank.io/api/data/Android/10/1
 http://gank.io/api/data/福利/10/1
 http://gank.io/api/data/iOS/20/2
 http://gank.io/api/data/all/20/2
 */
public interface GankIoService {
    @GET("data/福利/{size}/{pageNum}")
    Observable<ApiResponse<List<Fuli>>> getFuli(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/Android/{size}/{pageNum}")
    Observable<ApiResponse<List<Android>>> getAndroid(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/iOS/{size}/{pageNum}")
    Observable<ApiResponse<List<Ios>>> getIos(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/休息视频/{size}/{pageNum}")
    Observable<ApiResponse<List<Video>>> getVideo(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/拓展资源/{size}/{pageNum}")
    Observable<ApiResponse<List<Extent>>> getExtent(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/前端/{size}/{pageNum}")
    Observable<ApiResponse<List<FrontEnd>>> getFrontEnd(@Path("size") int size, @Path("pageNum") int pageNum);

    @GET("data/all/{size}/{pageNum}")
    Observable<ApiResponse<List<All>>> getAll(@Path("size") int size, @Path("pageNum") int pageNum);

}
