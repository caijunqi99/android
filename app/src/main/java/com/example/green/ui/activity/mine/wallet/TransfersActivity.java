package com.example.green.ui.activity.mine.wallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.QueryPropertybean;
import com.example.green.bean.mine.wallet.PointTransformbean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.customs.SelectorImageView;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

public class TransfersActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.transform_type)
    TextView mTransformType;
    @BindView(R.id.check_card)
    SelectorImageView mCheckCard;
    @BindView(R.id.tv_card)
    TextView mTvCard;
    @BindView(R.id.check_code)
    SelectorImageView mCheckCode;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.usable_zichan)
    TextView mUsableZichan;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.input_money)
    EditText mInput;
    @BindView(R.id.select_all)
    TextView mSelectAll;
    @BindView(R.id.bt_transform)
    Button mBtTransform;
    private int TYPE;
    private String key;
    private String mAvailable;
    private static final String TAG = "TransfersActivity";

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mMoney.setText(Html.fromHtml("&yen;"));
        TYPE = 1;
        mCheckCard.toggle(true);
        mCheckCode.toggle(false);
        mTvCard.setTextColor(getResources().getColor(R.color.app_theme_color));
        mTransformType.setText("储值卡");

    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.QUERY_PROPERT, key);
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
        return R.layout.activity_transfers;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.QUERY_PROPERT:
                QueryPropertybean queryPropertybean = (QueryPropertybean) t[0];
                if (null != queryPropertybean && queryPropertybean.getCode().equals("200")) {
                    mAvailable = queryPropertybean.getResult().getAvailable();
                    Log.e(TAG, "onResponse: " + mAvailable);
                    mUsableZichan.setText(mAvailable);
                } else if (queryPropertybean.getCode().equals("10086")) {
                    showInfoDialog(queryPropertybean.getMessage());
                }
                break;
            case ApiConfig.TRANSFORM:
                PointTransformbean pointTransformbean = (PointTransformbean) t[0];
                if (null != pointTransformbean && pointTransformbean.getCode().equals("200")) {
                    LocalBroadcastManager.getInstance(this)
                            .sendBroadcast(new Intent(BaseActivity.TRANSFORM_SUCCESS)); // 发送更新钱包信息 广播
                    showSuccessDialog();
                } else {
                    toastActivity(pointTransformbean.getMessage());
                }
                break;
        }
    }

    private void showInfoDialog(String msg) {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);
        // 确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }

    private void showSuccessDialog() {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText("成功");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }

    /**
     * 隐藏键盘
     */

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    @OnClick({R.id.back, R.id.check_card, R.id.check_code, R.id.select_all, R.id.bt_transform})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.check_card:
                TYPE = 1;
                mCheckCard.toggle(true);
                mCheckCode.toggle(false);
                mTransformType.setText("储值卡");
                mTvCard.setTextColor(getResources().getColor(R.color.app_theme_color));
                mTvCode.setTextColor(getResources().getColor(R.color.c_242424));

                break;
            case R.id.check_code:
                TYPE = 2;
                mCheckCard.toggle(false);
                mCheckCode.toggle(true);
                mTransformType.setText("认筹股");
                mTvCard.setTextColor(getResources().getColor(R.color.c_242424));
                mTvCode.setTextColor(getResources().getColor(R.color.app_theme_color));

                break;
            case R.id.select_all:
                mInput.setText(mAvailable);
                break;
            case R.id.bt_transform:
                String point = mInput.getText().toString().trim();
                if (!TextUtils.isEmpty(point)) {
                    BigDecimal bigDecimal = new BigDecimal(point);// 输入金额
                    int result = bigDecimal.compareTo(new BigDecimal(0));  // 最低提现金额
                    if (1 == result) {
                        hideInput();
                        mPresenter.getData(ApiConfig.TRANSFORM, key, TYPE, point);
                    } else {
                        toastActivity("输入金额必须大于0");
                    }
                } else {
                    toastActivity("请输入转出金额");
                }
                break;
        }
    }

    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
