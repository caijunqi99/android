package com.example.green.adapter.homepage;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.HomePgaeList;

import java.util.List;

public class MyOptionsItemAdapter extends BaseQuickAdapter<HomePgaeList.ResultBean.MenuBean, BaseViewHolder> {
    public MyOptionsItemAdapter(int layoutResId, @Nullable List<HomePgaeList.ResultBean.MenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePgaeList.ResultBean.MenuBean item) {
        HomePgaeList.ResultBean.MenuBean menuBean = item;
        if (null != menuBean) {
            TextView option_tv = helper.getView(R.id.option_tv);
            option_tv.setText(menuBean.getAdv_title());
            ImageView imageView = helper.getView(R.id.option_icon);
            Glide.with(mContext).load(menuBean.getAdv_code()).into(imageView);

            helper.addOnClickListener(R.id.rl_menu_goods);
        }
    }
}
