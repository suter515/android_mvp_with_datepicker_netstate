package com.automic.app.raindemo.businessone.model;

import android.util.Log;

import com.automic.app.raindemo.businessone.bean.HStationRainHistory;
import com.automic.app.raindemo.http.Fault;

import java.util.List;

import rx.functions.Action1;

/**
 * 类注释：
 * Created by sujingtai on 2017/6/21 0021 下午 4:07
 */

public class RainHistoryInteractorImpl implements RainHistoryInteractor {
    @Override
    public void requestRainHistoryData(String stcd, String date,final OnRainHistoryFinishedListener listener) {
        RainStHistoryLoader rainStHistoryLoader=new RainStHistoryLoader();
        rainStHistoryLoader.getRainHistory(stcd,date).subscribe(new Action1<List<HStationRainHistory>>() {
            @Override
            public void call(List<HStationRainHistory> hStationRainHistories) {
                if(null != listener){
                    listener.onSuccess(hStationRainHistories);
                }
            }
        },new Action1<Throwable>(){

            @Override
            public void call(Throwable throwable) {
                Log.e("sjt", "error message:" + throwable.getMessage());
                if (throwable.getMessage()!=null){
                    if (listener!=null){
                        listener.onFailed();
                    }
                }
//                if (throwable instanceof Fault) {
//                    Fault fault = (Fault) throwable;
//                    Log.e("sjt","错误码为"+fault.getErrorCode());
//                    if (null!=listener){
//                        listener.onFailed();
//                    }
//                    if (fault.getErrorCode() == 404) {
//                        //错误处理
//                    } else if (fault.getErrorCode() == 500) {
//                        //错误处理
//                    } else if (fault.getErrorCode() == 501) {
//                        //错误处理
//                    }
//                }
            }
        });
    }

}
