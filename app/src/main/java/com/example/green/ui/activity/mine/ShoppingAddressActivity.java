package com.example.green.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.mine.MyAddressItemAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.ShoppingAddressListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.ui.activity.homepage.LoginActivity;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingAddressActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_place)
    ImageView mIvPlace;
    @BindView(R.id.site_recycler)
    RecyclerView mSiteRecycler;
    private String key;
    private List<ShoppingAddressListbean.ResultBean.AddressListBean> mAddressListBeans;
    private MyAddressItemAdapter mMyAddressItemAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");

        mAddressListBeans = new ArrayList<>();
        mMyAddressItemAdapter = new MyAddressItemAdapter(R.layout.layout_shoppingaddress_item, mAddressListBeans);
        mSiteRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSiteRecycler.setAdapter(mMyAddressItemAdapter);

        mMyAddressItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_editor:
                        Intent intent = new Intent(ShoppingAddressActivity.this, EditorSiteActivity.class);
                        intent.putExtra("address_id", mAddressListBeans.get(position).getAddress_id());
                        intent.putExtra("city_id", mAddressListBeans.get(position).getCity_id());
                        intent.putExtra("area_id", mAddressListBeans.get(position).getArea_id());
                        intent.putExtra("name", mAddressListBeans.get(position).getAddress_realname());
                        intent.putExtra("phone", mAddressListBeans.get(position).getAddress_mob_phone());
                        intent.putExtra("area", mAddressListBeans.get(position).getArea_info());
                        intent.putExtra("site", mAddressListBeans.get(position).getAddress_detail());
                        intent.putExtra("is_default", mAddressListBeans.get(position).getAddress_is_default());
                        startActivity(intent);
                        break;
                    case R.id.rl_address:
                        Intent intent_site = getIntent();
                        int tag = intent_site.getIntExtra("tag", 0);
                        if (tag == 1) {
                            //这里使用bundle绷带 来传输数据
                            Bundle bundle = new Bundle();
                            //传输的内容仍然是键值对的形式
                            bundle.putString("area", mAddressListBeans.get(position).getArea_info());//回发的消息 地区
                            bundle.putString("site", mAddressListBeans.get(position).getAddress_detail());//回发的消息 详细地址
                            bundle.putString("name", mAddressListBeans.get(position).getAddress_realname());//回发的消息 姓名
                            bundle.putString("phone", mAddressListBeans.get(position).getAddress_mob_phone());//回发的消息 手机号
                            bundle.putString("addressId", mAddressListBeans.get(position).getAddress_id());//回发的消息 addressId
                            intent_site.putExtras(bundle);
                            setResult(RESULT_OK, intent_site);
                            finish();
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.SHOPPING_ADDRESS, key, LoadConfig.NORMAL);
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_address;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SHOPPING_ADDRESS:
                ShoppingAddressListbean shoppingAddressListbeans = (ShoppingAddressListbean) t[0];
                if (null != shoppingAddressListbeans && shoppingAddressListbeans.getCode().equals("200")) {
                    List<ShoppingAddressListbean.ResultBean.AddressListBean> address_list = shoppingAddressListbeans.getResult().getAddress_list();
                    if (null != address_list && address_list.size() > 0) {
                        mIvPlace.setVisibility(View.GONE);
                        mSiteRecycler.setVisibility(View.VISIBLE);
                        mAddressListBeans.addAll(address_list);
                    } else {
                        mIvPlace.setVisibility(View.VISIBLE);
                        mSiteRecycler.setVisibility(View.GONE);
                    }
                    mMyAddressItemAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.show(this, shoppingAddressListbeans.getMessage());
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.add_address})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.add_address:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);

        switch (intent.getAction()) {
            case ADD_ADDRESS:
            case EDITOR_ADDRESS:
            case DELETE_ADDRESS:
                mAddressListBeans.clear();
                mPresenter.getData(ApiConfig.SHOPPING_ADDRESS, key, LoadConfig.NORMAL);
                break;
        }
    }

}
