package com.example.green.adapter.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.TeamBean;

import java.util.List;

public class TeamAdapter extends BaseQuickAdapter<TeamBean.ResultBean.DatainfoBean, BaseViewHolder> {
    public TeamAdapter(int layoutResId, @Nullable List<TeamBean.ResultBean.DatainfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamBean.ResultBean.DatainfoBean item) {
        helper.setText(R.id.member_phone, item.getMember_mobile());
        helper.setText(R.id.member_empirical_value, item.getMember_exppoints());
        helper.setText(R.id.member_algbra, item.getLevel());
        String member_addtime = item.getMember_addtime();
        String[] split = member_addtime.split(" ");
        helper.setText(R.id.member_register_time, split[0]);
    }
}
