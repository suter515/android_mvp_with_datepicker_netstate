package com.automic.app.raindemo.views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.automic.app.raindemo.R;

/**
 * 类注释：无网弹框
 * Created by sujingtai on 2017/6/22 0022 下午 3:49
 */

public class NoNetDialog extends AlertDialog  {

    private View.OnClickListener mListener;
    private Context mContext;
    private TextView tvwNetInfo;
    private ImageView mImageView;
    public Button mBtnLoad;
    private String content;


    public NoNetDialog(Context context,String content, View.OnClickListener listener){
        super(context);
        this.mContext = context;
        this.content = content;
        this.mListener = listener;
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_net);
        tvwNetInfo = (TextView) findViewById(R.id.tvw_net_info);
        tvwNetInfo.setText(content);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
        mBtnLoad = (Button) findViewById(R.id.btn_load_again);
        mBtnLoad.setOnClickListener(mListener);
    }

    public void setBtnText(String text) {
        if (text != null && "".equals(text))
            mBtnLoad.setText(text);
    }
}
