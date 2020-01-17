package com.example.green.adapter.shoptrolley;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.pay.ShoppingInfobean;
import com.example.green.ui.activity.GoodsDetailsActivity;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.shoptrolley
 * @ClassName: OrderStoreItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 确认订单 店铺列表
 * @CreateDate: 2020/1/7 14:31
 */
public class OrderStoreItemAdapter extends BaseQuickAdapter<ShoppingInfobean.ResultBean.StoreCartListApiBean, BaseViewHolder> {

    public OrderStoreItemAdapter(int layoutResId, @Nullable List<ShoppingInfobean.ResultBean.StoreCartListApiBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingInfobean.ResultBean.StoreCartListApiBean item) {
        final ShoppingInfobean.ResultBean.StoreCartListApiBean storeCartListApiBean = item;
        if (null != storeCartListApiBean) {
            TextView storeName = helper.getView(R.id.mTv_storeName);
            RecyclerView child_goods = helper.getView(R.id.child_goods_recyclerview);

            String store_name = storeCartListApiBean.getStore_name();
            storeName.setText(store_name);
            OrderGoodsItemAdapter orderGoodsItemAdapter = new OrderGoodsItemAdapter(R.layout.layout_child_order_goods_item, storeCartListApiBean.getGoods());
            child_goods.setLayoutManager(new LinearLayoutManager(mContext));
            child_goods.setNestedScrollingEnabled(false); //禁止滑动
            child_goods.setAdapter(orderGoodsItemAdapter);

            orderGoodsItemAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.item_goods:
                            ShoppingInfobean.ResultBean.StoreCartListApiBean.GoodsBean goodsBean = storeCartListApiBean.getGoods().get(position);
                            int goods_id = goodsBean.getGoods_id();
                            Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                            intent.putExtra("goodsId", goods_id + "");
                            mContext.startActivity(intent);
                            break;
                    }
                }
            });
        }
    }

}
