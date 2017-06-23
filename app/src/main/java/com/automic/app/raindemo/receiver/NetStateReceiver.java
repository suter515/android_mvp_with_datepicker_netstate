package com.automic.app.raindemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.automic.app.raindemo.utils.NetWorkUtils;
import com.automic.app.raindemo.utils.ToastUtils;
import com.automic.app.raindemo.views.NoNetDialog;

/**
 * 类注释：网络状态receiver
 * Created by sujingtai on 2017/6/23 0023 上午 10:23
 */

public class NetStateReceiver extends BroadcastReceiver {
Context mContext;
    private NoNetDialog noNetDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext=context;
        int netType = NetWorkUtils.checkNetworkType(context);
        //网络状态良好
        if (netType != NetWorkUtils.TYPE_NET_WORK_DISABLED) {
            return;
        } else {
            nonetPlay(context);

        }
    }

    private void nonetPlay(Context context) {
        //为了避免冲突，只toast
        ToastUtils.show(context,"网络连接失败，请设置网络！");

       // 下方为断网显示对话框
//        noNetDialog = new NoNetDialog(context,"当前没有网络",listener);
//        noNetDialog.setBtnText("设置网络");
//        noNetDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        noNetDialog.show();
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 跳转到系统的网络设置界面
            Intent intent = null;
            // 先判断当前系统版本
            if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
                intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }else{
                intent = new Intent();
                intent.setClassName(mContext,"com.android.settings.WirelessSettings");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            mContext.startActivity(intent);
            if (noNetDialog.isShowing()){
                noNetDialog.dismiss();
            }
        }
    };
}
