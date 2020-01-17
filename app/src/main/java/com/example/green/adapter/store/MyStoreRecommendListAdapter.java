package com.example.green.adapter.store;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.store.StoreRecommendListbean;

import java.util.List;

public class MyStoreRecommendListAdapter extends BaseQuickAdapter<StoreRecommendListbean.ResultBean.RecGoodsListBean, BaseViewHolder> {

    public MyStoreRecommendListAdapter(int layoutResId, @Nullable List<StoreRecommendListbean.ResultBean.RecGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreRecommendListbean.ResultBean.RecGoodsListBean item) {
        StoreRecommendListbean.ResultBean.RecGoodsListBean recGoodsListBean = item;
        if (null != recGoodsListBean) {
            ImageView iv = helper.getView(R.id.goods_iv);
            TextView info = helper.getView(R.id.goods_info);
            TextView price = helper.getView(R.id.price);

            info.setText(recGoodsListBean.getGoods_name());
            price.setText(Html.fromHtml("&yen;") + recGoodsListBean.getGoods_price());
            Glide.with(mContext).load(recGoodsListBean.getGoods_image_url()).into(iv);

            helper.addOnClickListener(R.id.rl_goods);
        }
    }
}
