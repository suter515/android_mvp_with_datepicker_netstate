package com.automic.app.raindemo.businessone.view;

import com.automic.app.raindemo.baseparts.BaseView;
import com.automic.app.raindemo.businessone.bean.HStationRainHistory;

import java.util.List;




public interface RainHistoryMvpView extends BaseView {
    void setListItem(List<HStationRainHistory> data);
    void showMessage(String message);

}
