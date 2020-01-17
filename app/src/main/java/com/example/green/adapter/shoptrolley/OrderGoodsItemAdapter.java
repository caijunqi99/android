package com.example.green.adapter.shoptrolley;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.pay.ShoppingInfobean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.shoptrolley
 * @ClassName: OrderGoodsItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 确认订单 商品列表
 * @CreateDate: 2020/1/7 14:49
 */
public class OrderGoodsItemAdapter extends BaseQuickAdapter<ShoppingInfobean.ResultBean.StoreCartListApiBean.GoodsBean, BaseViewHolder> {

    public OrderGoodsItemAdapter(int layoutResId, @Nullable List<ShoppingInfobean.ResultBean.StoreCartListApiBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingInfobean.ResultBean.StoreCartListApiBean.GoodsBean item) {
        ShoppingInfobean.ResultBean.StoreCartListApiBean.GoodsBean goodsBean = item;
        if (null != goodsBean) {
            ImageView orderIv = helper.getView(R.id.mImg_icon);
            TextView goodsInfo = helper.getView(R.id.mTv_commodityName);
            TextView payPrice = helper.getView(R.id.pay_price);
            TextView goodsNum = helper.getView(R.id.goods_num);


            Glide.with(mContext).load(goodsBean.getGoods_image_url()).into(orderIv);
            goodsInfo.setText(goodsBean.getGoods_name());
            payPrice.setText(goodsBean.getGoods_price() + "元");
            goodsNum.setText("数量:" + goodsBean.getGoods_num());

            helper.addOnClickListener(R.id.item_goods);
        }
    }
}