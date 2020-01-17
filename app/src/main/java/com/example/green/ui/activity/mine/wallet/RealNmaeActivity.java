package com.example.green.ui.activity.mine.wallet;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.AddressBean;
import com.example.green.bean.mine.AuthInfobean;
import com.example.green.bean.mine.AutonymBean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.AreaPickerView;
import com.example.green.local_utils.MyDialogBottom;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RealNmaeActivity extends BaseMvpActivity<CommonPresenter, MineModel> implements ICommonView, MyDialogBottom.OnCenterItemClickListener {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Name)
    EditText mEtName;
    @BindView(R.id.et_IDnumber)
    EditText mEtIDnumber;
    @BindView(R.id.tv_Area)
    TextView mTvArea;
    @BindView(R.id.front_pic)
    ImageView mFrontPic;
    @BindView(R.id.reverse_pic)
    ImageView mReversePic;
    @BindView(R.id.hand_photo)
    ImageView mHandPic;
    @BindView(R.id.et_bank_name)
    EditText mEtBankName;
    @BindView(R.id.et_bank_account)
    EditText mEtBankAccount;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    private static final String TAG = "RealNmaeActivity";
    private int TYPE;
    //所选相册图片的路径(原图/压缩后/剪裁后)
    String FrontPic = "";
    String ReversePic = "";
    String HandPic = "";
    private AreaPickerView areaPickerView;
    private List<AddressBean.ResultBean.AreaListBeanXX> addressBeans;
    private int[] i;
    private String province_id;
    private String city_id;
    private String area_id;
    private MyDialogBottom mMyDialogBottom;
    private String userId;
    // 信息提交中加载
    ProgressDialog progressDialog;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        addressBeans = new ArrayList<>();
        Gson gson = new Gson();
        AddressBean addressBean = gson.fromJson(getCityJson(), AddressBean.class);
        Log.e(TAG, "initData: " + addressBean.toString());
        addressBeans.addAll(addressBean.getResult().getArea_list());
        areaPickerView = new AreaPickerView(this, R.style.Dialog, addressBeans);
        areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
            @Override
            public void callback(int... value) {
                i = value;
                if (value.length == 3) {
                    mTvArea.setText(addressBeans.get(value[0]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_list().get(value[2]).getArea_name());
                    province_id = addressBeans.get(value[0]).getArea_id() + "";
                    city_id = addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_id() + "";
                    area_id = addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_list().get(value[2]).getArea_id() + "";
                } else {
                    mTvArea.setText(addressBeans.get(value[0]).getArea_name()
                            + " " + addressBeans.get(value[0]).getArea_list().get(value[1]).getArea_name());
                }
            }
        });
    }

    @Override
    protected void initData() {
        Integer value = SPUtils.getInstance().getValue(SPUtils.KEY_USER_ID, 0);
        userId = String.valueOf(value);
        mPresenter.getData(ApiConfig.AUTHINFO, userId);
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
        return R.layout.activity_real_nmae;
    }

    @Override
    public void onError(Throwable e) {
        if (null != progressDialog) {
            dismissDialog();
        }
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.AUTHINFO:
                AuthInfobean authInfobean = (AuthInfobean) t[0];
                if (null != authInfobean && authInfobean.getCode() == 200) {
                    final AuthInfobean.ResultBean authInfobeanResult = authInfobean.getResult();
                    int member_auth_state = authInfobeanResult.getMember_auth_state();
                    Log.e(TAG, "onResponse: " + member_auth_state);
                    if (member_auth_state == 0) {
                        // 默认
                    } else if (member_auth_state == 2) {
                        toastActivity("审核未通过!!! 请重新提交信息");
                        mEtName.setText(authInfobeanResult.getUsername());
                        mEtIDnumber.setText(authInfobeanResult.getIdcard());
                        mTvArea.setText(authInfobeanResult.getMember_areainfo());

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image1())
                                .into(mHandPic);


                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image2())
                                .into(mFrontPic);

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image3())
                                .into(mReversePic);

                        mEtBankName.setText(authInfobeanResult.getMember_bankname());
                        mEtBankAccount.setText(authInfobeanResult.getMember_bankcard());
                        province_id = authInfobeanResult.getMember_provinceid() + "";
                        city_id = authInfobeanResult.getMember_cityid() + "";
                        area_id = authInfobeanResult.getMember_areaid() + "";
                    } else if (member_auth_state == 1) {
                        mEtName.setText(authInfobeanResult.getUsername());
                        mEtIDnumber.setText(authInfobeanResult.getIdcard());
                        mTvArea.setText(authInfobeanResult.getMember_areainfo());

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image1())
                                .into(mHandPic);

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image2())
                                .into(mFrontPic);

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image3())
                                .into(mReversePic);

                        mEtBankName.setText(authInfobeanResult.getMember_bankname());
                        mEtBankAccount.setText(authInfobeanResult.getMember_bankcard());
                        province_id = authInfobeanResult.getMember_provinceid() + "";
                        city_id = authInfobeanResult.getMember_cityid() + "";
                        area_id = authInfobeanResult.getMember_areaid() + "";
                        mBtnCommit.setText("审核中");
                        mEtName.setKeyListener(null);
                        mEtIDnumber.setKeyListener(null);
                        mTvArea.setClickable(false);
                        mFrontPic.setClickable(false);
                        mReversePic.setClickable(false);
                        mHandPic.setClickable(false);
                        mEtBankName.setKeyListener(null);
                        mEtBankAccount.setKeyListener(null);
                        mBtnCommit.setTextColor(getResources().getColor(R.color.c_242424));
                        mBtnCommit.setClickable(false);
                        mBtnCommit.setBackgroundResource(R.drawable.commit_bg);
                    } else if (member_auth_state == 3) {
                        mEtName.setText(authInfobeanResult.getUsername());
                        mEtIDnumber.setText(authInfobeanResult.getIdcard());
                        mTvArea.setText(authInfobeanResult.getMember_areainfo());

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image1())
                                .into(mHandPic);

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image2())
                                .into(mFrontPic);

                        Glide.with(RealNmaeActivity.this)
                                .asBitmap()
                                .load(authInfobeanResult.getMember_idcard_image3())
                                .into(mReversePic);

                        mEtBankName.setText(authInfobeanResult.getMember_bankname());
                        mEtBankAccount.setText(authInfobeanResult.getMember_bankcard());
                        province_id = authInfobeanResult.getMember_provinceid() + "";
                        city_id = authInfobeanResult.getMember_cityid() + "";
                        area_id = authInfobeanResult.getMember_areaid() + "";
                        mBtnCommit.setText("已认证");
                        mEtName.setKeyListener(null);
                        mEtIDnumber.setKeyListener(null);
                        mTvArea.setClickable(false);
                        mFrontPic.setClickable(false);
                        mReversePic.setClickable(false);
                        mHandPic.setClickable(false);
                        mEtBankName.setKeyListener(null);
                        mEtBankAccount.setKeyListener(null);
                        mBtnCommit.setClickable(false);
                    }
                }
                break;
            case ApiConfig.USER_AUTONYM:
                AutonymBean autonymBean = (AutonymBean) t[0];
                if (null != autonymBean && autonymBean.getCode() == 200) {
                    toastActivity("提交成功");
                    dismissDialog();
                    mPresenter.getData(ApiConfig.AUTHINFO, userId);

                } else {
                    toastActivity(autonymBean.getMessage());
                    dismissDialog();
                }
                break;
            case ApiConfig.USER_AUTONYMTWICE:
                AutonymBean autonymBeanTwice = (AutonymBean) t[0];
                if (null != autonymBeanTwice && autonymBeanTwice.getCode() == 200) {
                    toastActivity("提交成功");

                    mPresenter.getData(ApiConfig.AUTHINFO, userId);
                    dismissDialog();
                } else {
                    toastActivity(autonymBeanTwice.getMessage());
                    dismissDialog();
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.tv_Area, R.id.front_pic, R.id.reverse_pic, R.id.hand_photo, R.id.btn_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_Area:
                hideInput();
                areaPickerView.setSelect(i);
                areaPickerView.show();
                break;
            case R.id.front_pic:
                TYPE = 1;
                hideInput();
                showSelectPictureDialog();
                break;
            case R.id.reverse_pic:
                TYPE = 2;
                hideInput();
                showSelectPictureDialog();
                break;
            case R.id.hand_photo:
                TYPE = 3;
                hideInput();
                showSelectPictureDialog();
                break;
            case R.id.btn_commit:
                Integer userId = SPUtils.getInstance().getValue(SPUtils.KEY_USER_ID, 0);
                String name = mEtName.getText().toString().trim();
                String Idnumber = mEtIDnumber.getText().toString().trim();
                String area = mTvArea.getText().toString().trim();
                String bankname = mEtBankName.getText().toString().trim();
                String account = mEtBankAccount.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(Idnumber) && !TextUtils.isEmpty(area) && !TextUtils.isEmpty(bankname) && !TextUtils.isEmpty(account)) {
                    if (isIDNumber(Idnumber)) {
                        if (!HandPic.equals("") && !FrontPic.equals("") && !ReversePic.equals("")) {
                            mPresenter.getData(ApiConfig.USER_AUTONYM, userId, name, Idnumber, bankname, account,
                                    HandPic, FrontPic, ReversePic, province_id, city_id, area_id, area, 1);
                            initProgressDialog();
                        } else {
                            mPresenter.getData(ApiConfig.USER_AUTONYMTWICE, userId, name, Idnumber, bankname, account, province_id, city_id, area_id, area, 1);
                            initProgressDialog();
                        }
                    } else {
                        toastActivity("身份证号有误");
                    }
                } else {
                    toastActivity("请完善信息");
                }

                break;
        }
    }

    /*验证是否为身份证号*/
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression =
                "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                        + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = IDNumber.matches(regularExpression);
        // 判断第18位校验值
        if (matches) {
            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    // 前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    // 这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase()
                            .equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase()
                                + "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        }
        return matches;
    }

    /**
     * 选择图片弹框
     */
    private void showSelectPictureDialog() {
        mMyDialogBottom = new MyDialogBottom(this, R.layout.dialog_bottom_select_pictrue, new int[]
                {R.id.tv_select_pictrue_album, R.id.tv_select_pictrue_camera, R.id.tv_select_pictrue_cancel});
        mMyDialogBottom.setOnCenterItemClickListener(this);
        mMyDialogBottom.setCanceledOnTouchOutside(false);// 设置外部点击消失
        mMyDialogBottom.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);

                    if (localMedia != null) {
                        if (localMedia.size() > 0) {
                            LocalMedia localMedia1 = localMedia.get(0);
                            if (localMedia1.isCut()) {


                                String path = localMedia1.getCutPath();
                                if (TYPE == 1) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mFrontPic);
                                    FrontPic = path;
                                } else if (TYPE == 2) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mReversePic);
                                    ReversePic = path;
                                } else if (TYPE == 3) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mHandPic);
                                    HandPic = path;
                                }
                            } else {
                                String path = localMedia1.getPath();
                                if (TYPE == 1) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mFrontPic);
                                    FrontPic = path;
                                } else if (TYPE == 2) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mReversePic);
                                    ReversePic = path;
                                } else if (TYPE == 3) {
                                    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                                    Glide.with(RealNmaeActivity.this)
                                            .load(path).apply(options).into(mHandPic);
                                    HandPic = path;
                                }
                            }
                        }
                    }

                    break;
                case 200:

                    break;
            }
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

    @Override
    public void OnCenterItemClick(MyDialogBottom dialog, View view) {
        switch (view.getId()) {
            case R.id.tv_select_pictrue_album:

                PictureSelector.create(RealNmaeActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .previewImage(true)
                        .isZoomAnim(false)
                        .isCamera(false)
                        .imageFormat(PictureMimeType.JPEG)
                        .enableCrop(false)
                        .cropWH(400, 400)
                        .rotateEnabled(false)
                        .compress(true)
                        .withAspectRatio(1, 1)
                        .minimumCompressSize(200)
                        .isDragFrame(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                mMyDialogBottom.dismiss();

                break;
            case R.id.tv_select_pictrue_camera:

                PictureSelector.create(RealNmaeActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isZoomAnim(false)
                        .isCamera(true)
                        .imageFormat(PictureMimeType.JPEG)
                        .enableCrop(false)
                        .cropWH(400, 400)
                        .rotateEnabled(false)
                        .compress(true)
                        .withAspectRatio(1, 1)
                        .minimumCompressSize(200)
                        .isDragFrame(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);

                mMyDialogBottom.dismiss();

                break;
            case R.id.tv_select_pictrue_cancel:
                mMyDialogBottom.dismiss();
                break;
        }
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(RealNmaeActivity.this);
        progressDialog.setIndeterminate(false);//循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在审核");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        progressDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * 取消
     */
    private void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
