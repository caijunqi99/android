package com.example.green.adapter.mine.order;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.MyOrderbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.shoptrolley
 * @ClassName: GoodsChildOrderAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 子订单 （店铺）列表
 * @CreateDate: 2020/1/8 11:06
 */
public class ChildOrderItemAdapter extends BaseQuickAdapter<MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean, BaseViewHolder> {

    public ChildOrderItemAdapter(int layoutResId, @Nullable List<MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean item) {
        MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean orderListBean = item;
        if (null != orderListBean) {
            TextView storeName = helper.getView(R.id.mTv_storeName);
            RecyclerView goods_item = helper.getView(R.id.child_order_recyclerview);
            TextView stateChange = helper.getView(R.id.state_change);

            ChildOrderGoodsItemAdapter childOrderGoodsItemAdapter = new ChildOrderGoodsItemAdapter(R.layout.layout_child_order_goods_item, orderListBean.getGoods_list());
            goods_item.setLayoutManager(new LinearLayoutManager(mContext));
            goods_item.setNestedScrollingEnabled(false); //禁止滑动
            goods_item.setAdapter(childOrderGoodsItemAdapter);

            storeName.setText(orderListBean.getStore_name());
            int order_state = orderListBean.getOrder_state();
            if (order_state == 10) {
                stateChange.setVisibility(View.VISIBLE);
                stateChange.setText("取消订单");
            } else if (order_state == 20) {
                stateChange.setVisibility(View.GONE);
            } else if (order_state == 30) {
                stateChange.setVisibility(View.VISIBLE);
                stateChange.setText("确认收货");
            } else if (order_state == 40) {
                stateChange.setVisibility(View.VISIBLE);
                stateChange.setText("删除订单");
            } else if (order_state == 0) {
                stateChange.setVisibility(View.VISIBLE);
                stateChange.setText("删除订单");
            }
            // 拦截子itemview 点击事件
            goods_item.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.e(TAG, "onTouch: " + "click");
                    return helper.itemView.onTouchEvent(event);
                }
            });
            helper.addOnClickListener(R.id.item_goods);
            helper.addOnClickListener(R.id.state_change);
        }
    }
}
