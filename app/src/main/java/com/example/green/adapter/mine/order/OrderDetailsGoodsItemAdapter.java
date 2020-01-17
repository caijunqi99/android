package com.example.green.adapter.mine.order;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.OrderDetailsInfobean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.order
 * @ClassName: OrderDetailsGoodsItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 订单详情 商品列表
 * @CreateDate: 2020/1/8 14:29
 */
public class OrderDetailsGoodsItemAdapter extends BaseQuickAdapter<OrderDetailsInfobean.ResultBean.GoodsListBean, BaseViewHolder> {

    public OrderDetailsGoodsItemAdapter(int layoutResId, @Nullable List<OrderDetailsInfobean.ResultBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailsInfobean.ResultBean.GoodsListBean item) {
        OrderDetailsInfobean.ResultBean.GoodsListBean goodsListBean = item;
        if (null != goodsListBean) {
            ImageView orderIv = helper.getView(R.id.mImg_icon);
            TextView goodsInfo = helper.getView(R.id.mTv_commodityName);
            TextView payPrice = helper.getView(R.id.pay_price);
            TextView goodsNum = helper.getView(R.id.goods_num);

            Glide.with(mContext).load(goodsListBean.getGoods_image()).into(orderIv);
            goodsInfo.setText(goodsListBean.getGoods_name());
            payPrice.setText(goodsListBean.getGoods_price() + "元");
            goodsNum.setText("数量:" + goodsListBean.getGoods_num());

            helper.addOnClickListener(R.id.item_goods);
        }
    }
}
