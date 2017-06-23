package com.automic.app.raindemo.businessone.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.automic.app.raindemo.R;
import com.automic.app.raindemo.baseparts.BaseMvpActivity;
import com.automic.app.raindemo.businessone.bean.HStationRainHistory;
import com.automic.app.raindemo.businessone.presenter.RainHistoryPresenterImpl;
import com.automic.app.raindemo.businessone.view.RainHistoryMvpView;
import com.automic.app.raindemo.businessone.view.adapter.HydrologyRainHistoryAdapter;
import com.automic.app.raindemo.utils.ToastUtils;
import com.bigkoo.pickerview.TimePickerView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 业务一的主页面
 */
public class BusinessOneActivity extends BaseMvpActivity<RainHistoryMvpView, RainHistoryPresenterImpl> implements RainHistoryMvpView, AdapterView.OnItemClickListener {

    private ListView mvpListView;
    private ProgressBar pb;
    private TimePickerView pvTime;

    private Context mContext;
    private String dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mContext = BusinessOneActivity.this;
        mvpListView = (ListView) findViewById(R.id.mvp_listview);
        mvpListView.setOnItemClickListener(this);
        pb = (ProgressBar) findViewById(R.id.mvp_loading);
        TextView tvwDate = (TextView) findViewById(R.id.tvw_datepicker_time);
        tvwDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });

    }

    /**
     * 此界面的重新加载监听
     */
    private View.OnClickListener onceAgainlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogServerError.dismiss();
            ToastUtils.show(mContext, "重新加载");
            dialogLoading.show();
            presenter.getRainHistoryDataByDate("10000444", dateNow.substring(0, 7));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        initDatePicker();

    }

    private void initDatePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        // .setRangDate(startDate, endDate)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件(确认)回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                dateNow = getTime(date);
                ((TextView) v).setText(dateNow);
                dateNow = dateNow.replace('年', '-');
                dialogLoading.show();
                presenter.getRainHistoryDataByDate("10000444", dateNow.substring(0, 7));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                // .setRangDate(startDate, endDate)
                .setBackgroundId(R.color.pickerview_wheelview_textcolor_out) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }

    @Override
    public RainHistoryPresenterImpl initPresenter() {
        presenter = new RainHistoryPresenterImpl(this);
        return presenter;
    }

    @Override
    public View.OnClickListener initListener() {
        return onceAgainlistener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    public void setListItem(List<HStationRainHistory> data) {
        HydrologyRainHistoryAdapter adapter = new HydrologyRainHistoryAdapter(BusinessOneActivity.this, data);
        mvpListView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.show(this, message);
//        dialogLoading.dismiss();
//        dialogServerError.show();
        showDialog(dialogLoading,false);
showDialog(dialogServerError,true);
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.GONE);
        dialogLoading.show();
        // 动态设置自定义Dialog的显示内容的宽和高
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialogLoading.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = d.getHeight();   //高度设置为屏幕
        p.width = d.getWidth();    //宽度设置为全屏
        dialogLoading.getWindow().setAttributes(p);     //设置生效
        //showDialog(dialogLoading,true);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
       // dialogLoading.dismiss();
        showDialog(dialogLoading,false);
    }
}
