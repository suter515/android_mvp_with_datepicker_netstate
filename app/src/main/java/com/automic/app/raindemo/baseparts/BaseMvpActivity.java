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

            // 动态设置自定义Dialog的显示内容的宽和高
            WindowManager m = getWindowManager();
            Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
            p.height = (int) (d.getHeight() * 1);   //高度设置为屏幕的0.3
            p.width = d.getWidth();    //宽度设置为全屏
            dialog.getWindow().setAttributes(p);     //设置生效






            //一定得在show完dialog后来set属性
//            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//            lp.width= WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height=WindowManager.LayoutParams.MATCH_PARENT;
//            //获取屏幕的长宽
////        lp.width = AnimationTest.this.getResources().getDimensionPixelSize(R.dimen.dialog_width);
////        lp.height = AnimationTest.this.getResources().getDimensionPixelSize(R.dimen.dialog_height);
//            dialog.getWindow().setAttributes(lp);
            //获取屏幕的长宽
//            int screenHeight=0;
//            int screenWidth=0;
//            if(Build.VERSION.SDK_INT<23){
//                WindowManager window=getWindow().getWindowManager();
//                Display display=window.getDefaultDisplay();
//                 screenHeight = display.getHeight();
//                screenWidth = display.getWidth();
//            } else if (Build.VERSION.SDK_INT == 23) {
//                WindowManager windowManager = getWindow().getWindowManager();
//                DisplayMetrics dm = new DisplayMetrics();
//                windowManager.getDefaultDisplay().getMetrics(dm);
//                screenWidth = dm.widthPixels;
//                screenHeight = dm.heightPixels;
//            }
//
//            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//            lp.width = screenWidth;
//            lp.height = screenHeight;
//            //设置弹出框的长宽
//            dialog.getWindow().setGravity(Gravity.CENTER);
//            dialog.getWindow().setAttributes(lp);
            //dialog.getWindow().setLayout(screenWidth,screenHeight);
        }else if(dialog.isShowing()){
            dialog.dismiss();
        }

    }
    public abstract T initPresenter();
public abstract View.OnClickListener initListener();
}
