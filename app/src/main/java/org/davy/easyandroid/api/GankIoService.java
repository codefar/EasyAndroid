package org.davy.easyandroid.api;

import org.davy.easyandroid.bean.ApiResponse;
import org.davy.easyandroid.bean.FuliBean;

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
    Observable<ApiResponse<List<FuliBean>>> getFuli(@Path("size") int size, @Path("pageNum") int pageNum);
}
