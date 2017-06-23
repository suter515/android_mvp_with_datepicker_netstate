package com.automic.app.raindemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.automic.app.raindemo.businessone.view.activity.BusinessOneActivity;
import com.automic.app.raindemo.utils.AppUtils;
import com.automic.app.raindemo.utils.NetWorkUtils;
import com.automic.app.raindemo.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private int netState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        setupView();
    }

    private void setupView() {
        Button btnBusinessOne=(Button)findViewById(R.id.btn_business_one);
        btnBusinessOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netState = NetWorkUtils.checkNetworkType(mContext);
                if (netState!=NetWorkUtils.TYPE_NET_WORK_DISABLED){
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this, BusinessOneActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtils.show(MainActivity.this,"当前无网络连接，请设置网络");
                }
            }
        });
    }
}
