package com.example.green.adapter.mine.order;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.MyOrderbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine
 * @ClassName: GoodsOrderChildAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 子订单 商品列表
 * @CreateDate: 2020/1/7 11:09
 */
public class ChildOrderGoodsItemAdapter extends BaseQuickAdapter<MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean.GoodsListBean, BaseViewHolder> {

    public ChildOrderGoodsItemAdapter(int layoutResId, @Nullable List<MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean.GoodsListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean.GoodsListBean item) {
        MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean.GoodsListBean goodsListBean = item;
        if (null != goodsListBean) {

            ImageView orderIv = helper.getView(R.id.mImg_icon);
            TextView goodsInfo = helper.getView(R.id.mTv_commodityName);
            TextView payPrice = helper.getView(R.id.pay_price);
            TextView goodsNum = helper.getView(R.id.goods_num);

            Glide.with(mContext).load(goodsListBean.getGoods_image()).into(orderIv);
            goodsInfo.setText(goodsListBean.getGoods_name());
            payPrice.setText(goodsListBean.getGoods_price() + "元");
            goodsNum.setText("数量:" + goodsListBean.getGoods_num());

        }
    }
}
