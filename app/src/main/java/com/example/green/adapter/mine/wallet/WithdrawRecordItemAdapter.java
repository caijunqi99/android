package com.example.green.adapter.mine.wallet;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.wallet.WithdrawRecordListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.wallet
 * @ClassName: WithdrawRecordItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 提现记录
 * @CreateDate: 2020/1/3 16:17
 */
public class WithdrawRecordItemAdapter extends BaseQuickAdapter<WithdrawRecordListbean.ResultBean.ListBean, BaseViewHolder> {

    public WithdrawRecordItemAdapter(int layoutResId, @Nullable List<WithdrawRecordListbean.ResultBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawRecordListbean.ResultBean.ListBean item) {
        WithdrawRecordListbean.ResultBean.ListBean listBean = item;
        if (null != listBean) {
            TextView pdcAmount = helper.getView(R.id.pdc_amount);
            TextView pdcAddtime = helper.getView(R.id.pdc_addtime);
            TextView pdcPayment_state = helper.getView(R.id.pdc_payment_state);

            pdcAmount.setText("提现金额：" + listBean.getPdc_amount());
            pdcAddtime.setText("申请时间：" + listBean.getPdc_add_time_text());
            pdcPayment_state.setText("支付状态：" + listBean.getPdc_payment_state_text());
        }
    }
}
