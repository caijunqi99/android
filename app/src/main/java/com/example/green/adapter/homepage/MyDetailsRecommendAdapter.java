package com.example.green.adapter.homepage;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.DetailsDatabean;

import java.util.List;

public class MyDetailsRecommendAdapter extends BaseQuickAdapter<DetailsDatabean.ResultBean.GoodsCommendListBean, BaseViewHolder> {
    public MyDetailsRecommendAdapter(int layoutResId, @Nullable List<DetailsDatabean.ResultBean.GoodsCommendListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailsDatabean.ResultBean.GoodsCommendListBean item) {
        DetailsDatabean.ResultBean.GoodsCommendListBean goodsCommendListBean = item;
        if (null != goodsCommendListBean) {
            TextView name = helper.getView(R.id.goods_name);
            name.setText(goodsCommendListBean.getGoods_name());
            ImageView imageView = helper.getView(R.id.goods_iv);
            /*RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                    .fallback(R.mipmap.ic_launcher) //url为空的时候,显示的图片
                    .error(R.mipmap.ic_launcher)
                    .centerCrop();//*/
            // 图片加载失败后，显示的图片
            if (null != goodsCommendListBean.getGoods_image_url()) {
                Glide.with(mContext).load(goodsCommendListBean.getGoods_image_url())/*.apply(options)*/.into(imageView);
            }
            helper.addOnClickListener(R.id.goods_iv);
            helper.addOnClickListener(R.id.goods_name);
            helper.addOnClickListener(R.id.goods_card);
        }
    }
}
