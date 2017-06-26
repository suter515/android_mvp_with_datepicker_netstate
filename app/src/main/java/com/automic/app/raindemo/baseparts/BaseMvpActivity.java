package com.automic.app.raindemo.baseparts;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.automic.app.raindemo.R;
import com.automic.app.raindemo.views.CustomProgressDialog;
import com.automic.app.raindemo.views.NoNetDialog;

import java.lang.reflect.Method;

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
        dialogLoading = new CustomProgressDialog(this,"加载中...",R.style.Dialog_Fullscreen, R.drawable.dialog_loading);
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

    /**控制dialog的显示和隐藏
     * @param dialog
     * @param isDisplay true 展示，false 显示
     */
    public void showDialog(Dialog dialog,boolean isDisplay){
        if(isDisplay){
            dialog.show();
            //获取对话框当前的参数值
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
            // 动态设置自定义Dialog的显示内容的宽和高
            if(Build.VERSION.SDK_INT<17){
//                            WindowManager m = getWindowManager();
//                            Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
//                            p.height = d.getHeight();   //高度设置为屏幕的0.3
//                            p.width = d.getWidth();    //宽度设置为全屏
                //自动减去虚拟按键的高度
                Display display = getWindowManager().getDefaultDisplay();
                DisplayMetrics dm = new DisplayMetrics();
                @SuppressWarnings("rawtypes")
                Class c;
                try {
                    c = Class.forName("Android.view.Display");
                    @SuppressWarnings("unchecked")
                    Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
                    method.invoke(display, dm);
                }catch(Exception e){
                    e.printStackTrace();
                }
                p. width = dm.widthPixels;
                p.height = dm.heightPixels;

            } else if (Build.VERSION.SDK_INT >= 17) {
//                WindowManager windowManager = getWindow().getWindowManager();
//                DisplayMetrics dm = new DisplayMetrics();
//                windowManager.getDefaultDisplay().getMetrics(dm);
//                p.width = dm.widthPixels;
//                p.height = dm.heightPixels;
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getRealMetrics(dm);
              p.width = dm.widthPixels;  // 屏幕宽
               p.height = dm.heightPixels;  // 屏幕高

            }
            dialog.getWindow().setAttributes(p);     //设置生效
        }else if(dialog.isShowing()){
            dialog.dismiss();
        }

    }
    public abstract T initPresenter();
public abstract View.OnClickListener initListener();
}
