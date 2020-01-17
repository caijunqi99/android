package com.example.green.adapter.mine.order;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.LogisticsInfobean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.order
 * @ClassName: LogisticsItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 物流信息
 * @CreateDate: 2020/1/8 15:24
 */
public class LogisticsItemAdapter extends BaseQuickAdapter<LogisticsInfobean.ResultBean, BaseViewHolder> {
    public LogisticsItemAdapter(int layoutResId, @Nullable List<LogisticsInfobean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsInfobean.ResultBean item) {
        LogisticsInfobean.ResultBean logisticsbean = item;
        if (null != logisticsbean) {
            TextView acceptTime = helper.getView(R.id.accept_time);
            TextView acceptStation = helper.getView(R.id.accept_station);

            acceptTime.setText(logisticsbean.getAcceptTime());
            acceptStation.setText(logisticsbean.getAcceptStation());
        }
    }
}
