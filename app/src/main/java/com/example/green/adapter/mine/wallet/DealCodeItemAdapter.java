package com.example.green.adapter.mine.wallet;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.wallet.DealCodeListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.wallet
 * @ClassName: DealCodeItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 交易码交易明细适配器
 * @CreateDate: 2020/1/3 13:51
 */
public class DealCodeItemAdapter extends BaseQuickAdapter<DealCodeListbean.ResultBean.ListBean, BaseViewHolder> {

    public DealCodeItemAdapter(int layoutResId, @Nullable List<DealCodeListbean.ResultBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DealCodeListbean.ResultBean.ListBean item) {
        DealCodeListbean.ResultBean.ListBean listBean = item;
        if (null != listBean) {
            TextView date = helper.getView(R.id.date);
            TextView info = helper.getView(R.id.info);

            date.setText(listBean.getTl_add_time_text());
            info.setText(listBean.getTl_desc());
        }
    }
}
