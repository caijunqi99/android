package com.example.green.adapter.store;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.store.AllStoreListbean;

import java.util.List;


public class MyAllStoreListAdapter extends BaseQuickAdapter<AllStoreListbean.ResultBean.GoodsListBean, BaseViewHolder> {

    public MyAllStoreListAdapter(int layoutResId, @Nullable List<AllStoreListbean.ResultBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllStoreListbean.ResultBean.GoodsListBean item) {
        AllStoreListbean.ResultBean.GoodsListBean goodsListBean = item;
        if (null != goodsListBean) {
            ImageView iv = helper.getView(R.id.goods_iv);
            TextView info = helper.getView(R.id.goods_info);
            TextView price = helper.getView(R.id.price);

            info.setText(goodsListBean.getGoods_name());
            price.setText(Html.fromHtml("&yen;") + item.getGoods_price());
            Glide.with(mContext).load(goodsListBean.getGoods_image_url()).into(iv);

            helper.addOnClickListener(R.id.rl_goods);
        }
    }
}
