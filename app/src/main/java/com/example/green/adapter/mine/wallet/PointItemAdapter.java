package com.example.green.adapter.mine.wallet;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.wallet.PointListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.wallet
 * @ClassName: PointItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 积分交易明细适配器
 * @CreateDate: 2020/1/3 12:31
 */
public class PointItemAdapter extends BaseQuickAdapter<PointListbean.ResultBean.LogListBean, BaseViewHolder> {

    public PointItemAdapter(int layoutResId, @Nullable List<PointListbean.ResultBean.LogListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PointListbean.ResultBean.LogListBean item) {
        PointListbean.ResultBean.LogListBean logListBean = item;
        if (null != logListBean) {
            TextView date = helper.getView(R.id.date);
            TextView info = helper.getView(R.id.info);

            date.setText(logListBean.getAddtimetext());
            info.setText(logListBean.getPl_desc());
        }
    }
}
