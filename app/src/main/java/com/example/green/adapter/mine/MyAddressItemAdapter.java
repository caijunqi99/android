package com.example.green.adapter.mine;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.mine.ShoppingAddressListbean;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine
 * @ClassName: MyAddressItemAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 收货地址适配器类
 * @CreateDate: 2019/12/18 13:58
 */
public class MyAddressItemAdapter extends BaseQuickAdapter<ShoppingAddressListbean.ResultBean.AddressListBean, BaseViewHolder> {
    public MyAddressItemAdapter(int layoutResId, @Nullable List<ShoppingAddressListbean.ResultBean.AddressListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingAddressListbean.ResultBean.AddressListBean item) {
        ShoppingAddressListbean.ResultBean.AddressListBean addressListBean = item;
        if (null != addressListBean) {
            TextView name = helper.getView(R.id.recipients);
            TextView phone = helper.getView(R.id.phone);
            TextView area_info = helper.getView(R.id.area_info);
            TextView address_detail = helper.getView(R.id.address_detail);
            TextView default_address = helper.getView(R.id.default_address);

            name.setText(addressListBean.getAddress_realname());
            phone.setText(addressListBean.getAddress_mob_phone());
            area_info.setText(addressListBean.getArea_info());
            address_detail.setText(addressListBean.getAddress_detail());
            if (addressListBean.getAddress_is_default().equals("1")) {
                default_address.setVisibility(View.VISIBLE);
            } else {
                default_address.setVisibility(View.GONE);
            }

            helper.addOnClickListener(R.id.rl_editor);
            helper.addOnClickListener(R.id.rl_address);
        }
    }
}
