package com.automic.app.raindemo.businessone.model;

import com.automic.app.raindemo.businessone.bean.RainHistoryRespond;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类注释：雨量测站历史 retrofit服务
 * Created by sujingtai on 2017/6/20 0020 下午 3:18
 */

public interface RainStationInfoService {
    @GET("pp/queryHistoryRain")
    Observable<RainHistoryRespond> getAllAreaInfo(@Query("stcd") String stcd, @Query("queryDate")String date);
}
