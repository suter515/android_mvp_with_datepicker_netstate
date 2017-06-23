package com.automic.app.raindemo.businessone.bean;

import java.io.Serializable;

/**
 * 类注释：历史雨量bean
 * Created by sujingtai on 2017/6/2 0002 下午 3:48
 */

public class HStationRainHistory implements Serializable{
    private String dateDay;
    private float countDay;

    public String getDateDay() {
        return dateDay;
    }

    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public float getCountDay() {
        return countDay;
    }

    public void setCountDay(float countDay) {
        this.countDay = countDay;
    }
}
