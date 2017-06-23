package com.automic.app.raindemo.businessone.presenter;

/**
 * 类注释：
 * Created by sujingtai on 2017/6/21 0021 下午 4:47
 */

public interface RainHistoryPresenter {
    //void onresume();
    void onItemClick(int position);
    void getRainHistoryDataByDate(String stcd, String date);
}
