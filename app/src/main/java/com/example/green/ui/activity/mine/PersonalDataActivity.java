package com.example.green.ui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.base.BaseFragment;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.EditMineInfobean;
import com.example.green.bean.mine.MineInfobean;
import com.example.green.bean.mine.PictureUploadBean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.MyDialogBottom;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalDataActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView, MyDialogBottom.OnCenterItemClickListener {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.tv_editor)
    TextView mTvEditor;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_iv)
    ImageView mUserIv;
    @BindView(R.id.rl_icon)
    RelativeLayout mRlIcon;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.account)
    RelativeLayout mAccount;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.username)
    RelativeLayout mUsername;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.iv_editor)
    ImageView mIvEditor;

    private int Clicked = 0;
    private String key;
    private static final String TAG = "PersonalDataActivity";
    private int COMMIT_TYPE = 1; // 默认为0 获取用户信息  1 为修改信息
    private String SEX = ""; // 性别
    private String EMAIL = ""; // 邮箱
    private MineInfobean.ResultBean.MemberInfoBean mMember_info;
    private MyDialogBottom mMyDialogBottom;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        // 账号
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");

        if (Clicked == 0) {
            mRlIcon.setClickable(false);
            mEtName.setVisibility(View.GONE);
            mIvEditor.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.MINEINFO, key, LoadConfig.NORMAL);
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
        return R.layout.activity_personal_data;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e
                .getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MINEINFO:
                MineInfobean mineInfobeans = (MineInfobean) t[0];
                if (null != mineInfobeans && mineInfobeans.getCode().equals("200")) {
                    mMember_info = mineInfobeans.getResult().getMember_info();
                    RequestOptions options = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .circleCrop();
                    Glide.with(this).load(mMember_info.getAvator()).apply(options).into(mUserIv);

                    Log.e(TAG, "onResponse: " + mMember_info.getAvator());
                    mName.setText(mMember_info.getUser_name());
                    mPhone.setText(mMember_info.getMobile());
                } else {
                    toastActivity(mineInfobeans.getMessage());
                }
                break;
            case ApiConfig.URL_UPLOAD_PICTURE://上传图片 back
                PictureUploadBean uploadBean = (PictureUploadBean) t[0];
                if (uploadBean.getCode().equals("200")) {
                    if (null != uploadBean.getResult()) {
                        PictureUploadBean.ResultBean data = uploadBean.getResult();
                        if (data.getAvatar() != null) {
                            String avatar = data.getAvatar();
                            Log.e(TAG, "onResponse: " + avatar);
                            RequestOptions options = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .circleCrop();
                            Glide.with(this).load(avatar)
                                    .apply(options)
                                    .into(mUserIv);

                            updateInfoData(); //修改图片
                            mPresenter.getData(ApiConfig.MINEINFO, key, LoadConfig.NORMAL);
                        }
                    }
                } else {
                    toastActivity("图片上传失败");
                }
                break;
            case ApiConfig.URL_EDIT_USER_INFO:
                EditMineInfobean editMineInfobean = (EditMineInfobean) t[0];
                if (null != editMineInfobean && editMineInfobean.getCode().equals("200")) {
                    updateInfoData(); // 修改昵称
                } else {
                    toastActivity(editMineInfobean.getMessage());
                }
                break;
        }
    }

    /**
     * 更新上传数据
     */
    private void updateInfoData() {
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(new Intent(BaseFragment.RECTIFY_UPDATE_INFO)); // 发送修改个人资料广播
    }

    @OnClick({R.id.back, R.id.tv_editor, R.id.rl_icon})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_editor:
                if (Clicked % 2 == 0) {
                    mTvEditor.setText("完成");
                    mTvEditor.setTextColor(getResources().getColor(R.color.app_theme_color));
                    mRlIcon.setClickable(true);
                    mName.setVisibility(View.GONE);
                    mEtName.setVisibility(View.VISIBLE);
                    mIvEditor.setVisibility(View.VISIBLE);
                    mEtName.setText(mName.getText().toString().trim());

                } else {
                    mTvEditor.setText("编辑");
                    mTvEditor.setTextColor(getResources().getColor(R.color.c_242424));
                    mRlIcon.setClickable(false);
                    mName.setVisibility(View.VISIBLE);
                    mEtName.setVisibility(View.GONE);
                    mIvEditor.setVisibility(View.GONE);
                    mName.setText(mEtName.getText().toString().trim());
                    // 编辑资料
                    mPresenter.getData(ApiConfig.URL_EDIT_USER_INFO, key, COMMIT_TYPE, mName.getText().toString().trim(), SEX, EMAIL);
                }
                Clicked = Clicked + 1;
                //点击搜索的时候隐藏软键盘
                hideKeyboard(mTvEditor);
                break;
            case R.id.rl_icon:
                showSelectPictureDialog();
                break;
        }
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
                                Log.e(TAG, "onActivityResult: " + path);
                                mPresenter.getData(ApiConfig.URL_UPLOAD_PICTURE, path, key, LoadConfig.NORMAL);
                            } else {
                                String path = localMedia1.getPath();
                                Log.e(TAG, "onActivityResult: " + path);
                                mPresenter.getData(ApiConfig.URL_UPLOAD_PICTURE, path, key, LoadConfig.NORMAL);
                            }
                        }
                    }

                    break;
                case 200:

                    break;
            }
        }
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


    /**
     * 隐藏软键盘
     *
     * @param view :一般为EditText
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void OnCenterItemClick(MyDialogBottom dialog, View view) {
        switch (view.getId()) {
            case R.id.tv_select_pictrue_album:

                PictureSelector.create(PersonalDataActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .previewImage(true)
                        .isZoomAnim(false)
                        .isCamera(false)
                        .imageFormat(PictureMimeType.JPEG)
                        .enableCrop(true)
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

                PictureSelector.create(PersonalDataActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isZoomAnim(false)
                        .isCamera(true)
                        .imageFormat(PictureMimeType.JPEG)
                        .enableCrop(true)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mMyDialogBottom)
            mMyDialogBottom.dismiss();
    }
}
