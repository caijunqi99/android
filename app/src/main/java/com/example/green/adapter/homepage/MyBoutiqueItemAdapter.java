package com.example.green.adapter.homepage;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.HomePgaeList;

import java.util.List;

public class MyBoutiqueItemAdapter extends BaseQuickAdapter<HomePgaeList.ResultBean.DiscountBean.DateBean.GoodsInfoBean, BaseViewHolder> {
    public MyBoutiqueItemAdapter(int layoutResId, @Nullable List<HomePgaeList.ResultBean.DiscountBean.DateBean.GoodsInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePgaeList.ResultBean.DiscountBean.DateBean.GoodsInfoBean item) {
        HomePgaeList.ResultBean.DiscountBean.DateBean.GoodsInfoBean goodsInfoBean = item;
        if (null != goodsInfoBean) {
            TextView price = helper.getView(R.id.price);
            price.setText(Html.fromHtml("&yen;") + goodsInfoBean.getGoods_price());
//            TextView pre_price = helper.getView(R.id.pre_price);
//            pre_price.setText(Html.fromHtml("&yen;") + goodsInfoBean.getGoods_promotion_price());
//            pre_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ImageView imageView = helper.getView(R.id.goods);
            // 图片加载失败后，显示的图片
            if (null != goodsInfoBean.getGoods_image()) {
                Glide.with(mContext).load(goodsInfoBean.getGoods_image()).into(imageView);
            }
            helper.addOnClickListener(R.id.rl);
        }
    }
}
