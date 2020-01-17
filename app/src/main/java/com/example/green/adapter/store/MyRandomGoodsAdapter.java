package com.example.green.adapter.store;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.homepage.GoodsListbean;
import com.example.green.bean.store.RandomRecListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.store
 * @ClassName: MyRandomGoodsAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/26 18:27
 */

public class MyRandomGoodsAdapter extends BaseQuickAdapter<RandomRecListbean.ResultBean, BaseViewHolder> {

    public MyRandomGoodsAdapter(int layoutResId, @Nullable List<RandomRecListbean.ResultBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, RandomRecListbean.ResultBean item) {
        RandomRecListbean.ResultBean resultBean = item;
        if (null != resultBean) {
            TextView name = helper.getView(R.id.goods_name);
            name.setText(resultBean.getGoods_name());
            TextView price = helper.getView(R.id.goods_price);
            price.setText(Html.fromHtml("&yen;") + resultBean.getGoods_price());
            TextView pre_price = helper.getView(R.id.goods_preprice);
            pre_price.setText(Html.fromHtml("&yen;") + resultBean.getGoods_promotion_price());
            pre_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ImageView imageView = helper.getView(R.id.goods_iv);
/*            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                    .fallback(R.mipmap.ic_launcher) //url为空的时候,显示的图片
                    .error(R.mipmap.ic_launcher);*/
            // 图片加载失败后，显示的图片

            if (null != resultBean.getGoods_image()) {
                Glide.with(mContext).load(resultBean.getGoods_image()).into(imageView);
            }
            helper.addOnClickListener(R.id.goods_card);
        }
    }
}
