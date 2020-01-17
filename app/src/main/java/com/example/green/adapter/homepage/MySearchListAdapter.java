package com.example.green.adapter.homepage;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.SearchListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.homepage
 * @ClassName: MySearchListAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 搜索列表适配器
 * @CreateDate: 2019/12/16 10:52
 */
public class MySearchListAdapter extends BaseQuickAdapter<SearchListbean.ResultBean.GoodsListBean, BaseViewHolder> {

    public MySearchListAdapter(int layoutResId, @Nullable List<SearchListbean.ResultBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchListbean.ResultBean.GoodsListBean item) {
        SearchListbean.ResultBean.GoodsListBean goodsListBean = item;
        if (null != goodsListBean) {
            TextView info = helper.getView(R.id.goods_info);
            TextView price = helper.getView(R.id.goods_price);
            TextView pre_price = helper.getView(R.id.goods_pre_price);
            TextView sales = helper.getView(R.id.sales_num);
//            ImageView view = helper.getView(R.id.new_product);
            ImageView imageView = helper.getView(R.id.iv_goods);
            info.setText(goodsListBean.getGoods_name());
            price.setText(Html.fromHtml("&yen;") + goodsListBean.getGoods_price());
            pre_price.setText(Html.fromHtml("&yen;") + goodsListBean.getGoods_marketprice());
            pre_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            sales.setText("总销量:" + goodsListBean.getGoods_salenum());
            /*if (goodsListBean.isXianshi_flag()) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }*/
            Glide.with(mContext).load(goodsListBean.getGoods_image_url()).into(imageView);


            helper.addOnClickListener(R.id.rl_goods); // 商品详情
//            helper.addOnClickListener(R.id.add_cart); // 加入购物车
        }
    }
}
