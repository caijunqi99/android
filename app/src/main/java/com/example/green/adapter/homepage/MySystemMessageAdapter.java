package com.example.green.adapter.homepage;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.SystemMessageListbean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.homepage
 * @ClassName: MySystemMessageAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 消息适配器类
 * @CreateDate: 2019/12/31 18:46
 */
public class MySystemMessageAdapter extends BaseQuickAdapter<SystemMessageListbean.ResultBean, BaseViewHolder> {
    public MySystemMessageAdapter(int layoutResId, @Nullable List<SystemMessageListbean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMessageListbean.ResultBean item) {
        SystemMessageListbean.ResultBean message = item;
        if (null != message) {
            TextView messageType = helper.getView(R.id.message_type);
            TextView dateTime = helper.getView(R.id.datetime);
            TextView messageInfo = helper.getView(R.id.message_info);

            messageType.setText(message.getFrom_member_name());
            dateTime.setText(message.getTimes());
            messageInfo.setText(message.getMessage_body());
        }
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }
}
