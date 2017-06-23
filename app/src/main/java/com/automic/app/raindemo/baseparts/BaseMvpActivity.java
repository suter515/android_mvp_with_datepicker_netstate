package com.automic.app.raindemo.baseparts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.automic.app.raindemo.R;
import com.automic.app.raindemo.views.CustomProgressDialog;
import com.automic.app.raindemo.views.NoNetDialog;

public abstract class BaseMvpActivity<V extends BaseView,T extends NewBasePresenter<V>> extends AppCompatActivity {

    public T presenter;
    public CustomProgressDialog dialogLoading;
    public NoNetDialog dialogServerError;
    public NoNetDialog dialogNoNet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        //正在加载的dialog
        dialogLoading = new CustomProgressDialog(this,"正在加载", R.drawable.dialog_loading);
        dialogServerError = new NoNetDialog(this,"服务器开小差了",initListener());
//        dialogNoNet = new NoNetDialog(mContext,"没有网络连接，请设置网络",listener);
//        if (NetWorkUtils.checkNetworkType(mContext) == NetWorkUtils.TYPE_NET_WORK_DISABLED) {
//            dialogNoNet.show();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this,(V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    public abstract T initPresenter();
public abstract View.OnClickListener initListener();
}
