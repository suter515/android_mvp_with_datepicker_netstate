package com.automic.app.raindemo.businessone.presenter;


import android.os.Handler;
import android.os.Looper;

import com.automic.app.raindemo.baseparts.NewBasePresenter;
import com.automic.app.raindemo.businessone.bean.HStationRainHistory;
import com.automic.app.raindemo.businessone.model.RainHistoryInteractor;
import com.automic.app.raindemo.businessone.model.RainHistoryInteractorImpl;
import com.automic.app.raindemo.businessone.view.RainHistoryMvpView;
import com.automic.app.raindemo.utils.LogUtils;

import java.util.List;


public class RainHistoryPresenterImpl extends NewBasePresenter<RainHistoryMvpView> implements RainHistoryPresenter,RainHistoryInteractor.OnRainHistoryFinishedListener {

    private RainHistoryInteractor rainHistoryInteractor;
    private Handler mHandler;
    private RainHistoryMvpView mvpView;

    public RainHistoryPresenterImpl(RainHistoryMvpView mvpView) {
        this.rainHistoryInteractor = new RainHistoryInteractorImpl();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mvpView = mvpView;
    }
    @Override
    public void onSuccess(final List<HStationRainHistory> data) {
        LogUtils.e("sjt","当前线程"+Thread.currentThread().getName());
        LogUtils.e("sjt","数据个数"+data.size());
                LogUtils.e("sjt","当前线程"+Thread.currentThread().getName());
                mvpView.hideLoading();
        mvpView.setListItem(data);
    }

    @Override
    public void onFailed() {
        mvpView.showMessage("请求失败");
    }

    @Override
    public void onItemClick(int position) {

        mvpView.showMessage("点击了item" + position);
    }

    @Override
    public void getRainHistoryDataByDate(String stcd,String date) {
        mvpView.showLoading();
        rainHistoryInteractor.requestRainHistoryData(stcd,date,RainHistoryPresenterImpl.this);
    }

}
