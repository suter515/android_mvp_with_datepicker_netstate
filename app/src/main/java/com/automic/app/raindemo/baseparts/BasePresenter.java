package com.automic.app.raindemo.baseparts;


public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView){
        this.mView = mView;
    }

    /**
     * 解除引用
     */
    public void dettach(){
        mView = null;
    }
}
