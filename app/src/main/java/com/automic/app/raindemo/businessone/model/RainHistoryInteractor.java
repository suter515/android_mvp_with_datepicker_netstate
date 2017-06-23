package com.automic.app.raindemo.businessone.model;

import com.automic.app.raindemo.businessone.bean.HStationRainHistory;

import java.util.List;

/**
 * 类注释：雨量数据拦截器
 * Created by sujingtai on 2017/6/21 0021 下午 4:01
 */

public interface RainHistoryInteractor {
    public interface OnRainHistoryFinishedListener {

        void onSuccess(List<HStationRainHistory> data);
        void onFailed();
    }
    void requestRainHistoryData(String stcd,String date,OnRainHistoryFinishedListener listener);
}
