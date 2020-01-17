package com.example.green.ui.activity.mine;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.AddressBean;
import com.example.green.bean.mine.Logoutbean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.AreaPickerView;
import com.example.green.customs.SelectorImageView;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class EditorSiteActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.delete_site)
    TextView mDeleteSite;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_select_area)
    TextView mTvSelectArea;
    @BindView(R.id.et_detail_site)
    EditText mEtDetailSite;
    @BindView(R.id.check)
    SelectorImageView mCheck;
    @BindView(R.id.save_address)
    Button mSaveAddress;

    private AreaPickerView areaPickerView;
    private List<AddressBean.ResultBean.AreaListBeanXX> addressBeans;
    private int[] i;
    private String address_id;
    private String city_id;
    private String area_id;
    private String name;
    private String phone;
    private String area;
    private String site;
    private String is_default;
    private static final String TAG = "EditorSiteActivity";

    @Override
    protected void initView() {
        Intent intent = getIntent();
        address_id = intent.getStringExtra("address_id");
        city_id = intent.getStringExtra("city_id");
        area_id = intent.getStringExtra("area_id");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        area = intent.getStringExtra("area");
        site = intent.getStringExtra("site");
        is_default = intent.getStringExtra("is_default");
        Log.e(TAG, "initView: " + is_default);
        mEtName.setText(name);
        mEtPhone.setText(phone);
        mTvSelectArea.setText(area);
        mEtDetailSite.setText(site);
        if (is_default.equals("1")) {
            mCheck.toggle(true);
        } else {
            mCheck.toggle(false);
        }

        mEtPhone.addTextChangedListener(phoneEditInput); // 监听手机号输入状态
    }

    private TextWatcher phoneEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            String phone = mEtPhone.getText().toString().trim();
            Pattern pattern = Pattern.compile("\\d{11}");
            Matcher m = pattern.matcher(phone);
            if (m.matches()) {
                hideInput();
            }
        }
    };


    @Override
    protected void initData() {
        addressBeans = new ArrayList<>();
        Gson gson = new Gson();

        AddressBean addressBean = gson.fromJson(getCityJson(), AddressBean.class);
        Log.e(TAG, "initData: " + addressBean.toString());
        addressBeans.addAll(addressBean.getResult().getArea_list());
        Log.e(TAG, "initData: " + addressBeans.toString());
        areaPickerView = new AreaPickerView(this, R.style.Dialog, addressBeans);
        areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
            @Override
            public void callback(int... value) {
                i = value;
                if (value.length == 3) {
                    mTvSelectArea.setText(addressBeans.get(value[0]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_list().get(value[2]).getArea_name());
                    city_id = addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_id() + "";
                    area_id = addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_list().get(value[2]).getArea_id() + "";
                } else {
                    mTvSelectArea.setText(addressBeans.get(value[0]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_name());
                }
            }
        });
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
        return R.layout.activity_editor_site;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.EDITOR_ADDRESS:
                Logoutbean editorbean = (Logoutbean) t[0];
                if (null != editorbean && editorbean.getCode().equals("200")) {
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseActivity.EDITOR_ADDRESS));
                    finish();
                } else {
                    toastActivity(editorbean.getMessage());
                }
                break;
            case ApiConfig.DELETE_ADDRESS:
                Logoutbean deletebean = (Logoutbean) t[0];
                if (null != deletebean && deletebean.getCode().equals("200")) {
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseActivity.DELETE_ADDRESS));
                    finish();
                } else {
                    toastActivity(deletebean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.delete_site, R.id.rl_select_area, R.id.check, R.id.save_address})
    public void onClick(View v) {
        String userToken = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");

        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.delete_site:
                mPresenter.getData(ApiConfig.DELETE_ADDRESS, userToken, address_id);
                break;
            case R.id.rl_select_area:
                hideInput();
                areaPickerView.setSelect(i);
                areaPickerView.show();
                break;
            case R.id.check:
                mCheck.toggle(!mCheck.isChecked());
                break;
            case R.id.save_address:

                String name = mEtName.getText().toString().trim();
                String phone = mEtPhone.getText().toString().trim();
                String area_info = mTvSelectArea.getText().toString().trim();
                String address = mEtDetailSite.getText().toString().trim();
                if (mCheck.isChecked()) {
                    is_default = "1";
                } else {
                    is_default = "0";
                }
                mPresenter.getData(ApiConfig.EDITOR_ADDRESS, userToken, name, phone, city_id, area_id, address, area_info, is_default, address_id);
                break;
        }
    }

    private String getCityJson() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("area.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
