package com.example.green.adapter.mine;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.CollegeListbean;

import java.util.List;

public class MyCollegeAdapter extends BaseQuickAdapter<CollegeListbean.ResultBean, BaseViewHolder> {

    public MyCollegeAdapter(int layoutResId, @Nullable List<CollegeListbean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollegeListbean.ResultBean item) {
        CollegeListbean.ResultBean resultBean = item;
        if (null != item) {
            ImageView iv = helper.getView(R.id.college_iv);
            TextView title = helper.getView(R.id.title_tv);
            TextView date = helper.getView(R.id.date_tv);

            title.setText(resultBean.getArticle_title());
            date.setText(resultBean.getArticle_time());
            RequestOptions transform = new RequestOptions().transform(new RoundedCorners(20));
            Glide.with(mContext).load(resultBean.getArticle_pic()).apply(transform).into(iv);

            helper.addOnClickListener(R.id.rl_college_card);
        }
    }
}
