package com.example.green.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.Logoutbean;
import com.example.green.bean.mine.MineInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.ui.activity.homepage.LoginActivity;
import com.example.green.ui.activity.mine.LoginPswActivity;
import com.example.green.ui.activity.mine.MyOrderActivity;
import com.example.green.ui.activity.mine.PayPswActivity;
import com.example.green.ui.activity.mine.PersonalDataActivity;
import com.example.green.ui.activity.mine.ShoppingAddressActivity;
import com.example.green.ui.activity.mine.WalletActivity;
import com.example.green.ui.activity.mine.wallet.InviteActivity;
import com.yiyatech.utils.ext.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseMvpFragment<CommonPresenter, MineModel>
        implements ICommonView {

    static MineFragment fragment;

    @BindView(R.id.header)
    ImageView mHeader;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_phone)
    TextView mUserPhone;
    @BindView(R.id.rl_join)
    RelativeLayout mRlJoin;
    @BindView(R.id.tuiguang)
    RelativeLayout mTuiguang;
    @BindView(R.id.wait_pay_ll)
    RelativeLayout mWaitPayLl;
    @BindView(R.id.wait_pay_num)
    TextView mWaitPayNum;
    @BindView(R.id.wait_deliver_ll)
    RelativeLayout mWaitDeliverLl;
    @BindView(R.id.wait_deliver_num)
    TextView mWaitDeliverNum;
    @BindView(R.id.notakes_ll)
    RelativeLayout mNoTakes;
    @BindView(R.id.notakes_num)
    TextView mNoTakesNum;
    @BindView(R.id.finish_ll)
    RelativeLayout mFinishLl;
    @BindView(R.id.finish_num)
    TextView mFinishNum;
    @BindView(R.id.rl_info)
    RelativeLayout mRlInfo;
    @BindView(R.id.rl_login_password)
    RelativeLayout mRlLoginPassword;
    @BindView(R.id.rl_pay_password)
    RelativeLayout mRlPayPassword;
    @BindView(R.id.rl_site)
    RelativeLayout mRlSite;
    @BindView(R.id.rl_invite)
    RelativeLayout mRlInvite;
    @BindView(R.id.rl_quit)
    RelativeLayout mRlQuit;

    private String key;// token
    private static final String TAG = "MineFragment";
    private MineInfobean.ResultBean.MemberInfoBean mMember_info;

    public static MineFragment newInstance() {
        if (fragment == null) fragment = new MineFragment();
        return fragment;
    }

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        Log.e(TAG, "------token------: " + key);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.MINEINFO, key, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MINEINFO:
                MineInfobean mineInfobeans = (MineInfobean) t[0];
                if (null != mineInfobeans && mineInfobeans.getCode().equals("100")) {
                    ToastUtils.show(getContext(), mineInfobeans.getMessage());
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                } else if (null != mineInfobeans && mineInfobeans.getCode().equals("200")) {
                    mMember_info = mineInfobeans.getResult().getMember_info();

                    RequestOptions options = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .circleCrop();
                    Glide.with(getContext()).load(mMember_info.getAvator()).apply(options).into(mHeader);
                    Log.e(TAG, "onResponse: " + mMember_info.getAvator());
                    mUserName.setText(mMember_info.getUser_name());
                    mUserPhone.setText(mMember_info.getMobile());
                    /*待付款*/
                    if (0 != mMember_info.getOrder_nopay_count()) {
                        mWaitPayNum.setVisibility(View.VISIBLE);
                        mWaitPayNum.setText(mMember_info.getOrder_nopay_count() + "");
                    } else {
                        mWaitPayNum.setVisibility(View.GONE);
                    }
                    /*待发货*/
                    if (0 != mMember_info.getOrder_noreceipt_count()) {
                        mWaitDeliverNum.setVisibility(View.VISIBLE);
                        mWaitDeliverNum.setText(mMember_info.getOrder_noreceipt_count() + "");
                    } else {
                        mWaitDeliverNum.setVisibility(View.GONE);
                    }
                    /*待收货*/
                    if (0 != mMember_info.getOrder_notakes_count()) {
                        mNoTakesNum.setVisibility(View.VISIBLE);
                        mNoTakesNum.setText(mMember_info.getOrder_notakes_count() + "");
                    } else {
                        mNoTakesNum.setVisibility(View.GONE);
                    }
                    /*已完成*/
                    if (0 != mMember_info.getOrder_noeval_count()) {
                        mFinishNum.setVisibility(View.VISIBLE);
                        mFinishNum.setText(mMember_info.getOrder_noeval_count() + "");
                    } else {
                        mFinishNum.setVisibility(View.GONE);
                    }
                }
                break;
            case ApiConfig.LOGOUT:
                Logoutbean logoutbean = (Logoutbean) t[0];
                if (null != logoutbean && logoutbean.getCode().equals("200")) {
                    SPUtils.getInstance().removeValue(SPUtils.KEY_USER_TOKEN); // 清除Token
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
        }
    }

    private void showRuleDialog() {
        View view = View.inflate(getContext(), R.layout.dialog_draw_rule, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(getContext(), 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
//        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
//        tv_message.setText(msg);

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
            }
        });
    }

    @OnClick({R.id.header, R.id.rl_join, R.id.wait_pay_ll, R.id.wait_deliver_ll,
            R.id.notakes_ll, R.id.finish_ll, R.id.rl_info, R.id.rl_login_password,
            R.id.rl_pay_password, R.id.rl_site, R.id.rl_invite, R.id.rl_quit})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.header: // 头像
//                showRuleDialog();
                break;
            case R.id.rl_join: // 进入钱包
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.wait_pay_ll: // 待付款
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_wait_pay = new Intent(getContext(), MyOrderActivity.class);
                intent_wait_pay.putExtra("index", 1);
                startActivity(intent_wait_pay);
                break;
            case R.id.wait_deliver_ll: // 待发货
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_wait_deliver = new Intent(getContext(), MyOrderActivity.class);
                intent_wait_deliver.putExtra("index", 2);
                startActivity(intent_wait_deliver);
                break;
            case R.id.notakes_ll: // 待收货
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_finish = new Intent(getContext(), MyOrderActivity.class);
                intent_finish.putExtra("index", 3);
                startActivity(intent_finish);
                break;
            case R.id.finish_ll: // 已完成
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_cancel = new Intent(getContext(), MyOrderActivity.class);
                intent_cancel.putExtra("index", 4);
                startActivity(intent_cancel);
                break;
            case R.id.rl_info: // 个人资料
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(getContext(), PersonalDataActivity.class));
                break;
            case R.id.rl_login_password: // 重置登录密码
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(getContext(), LoginPswActivity.class));
                break;
            case R.id.rl_pay_password: // 重置支付密码
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(getContext(), PayPswActivity.class));
                break;
            case R.id.rl_site: // 收货地址
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(getContext(), ShoppingAddressActivity.class));
                break;
            case R.id.rl_invite: // 邀请好友
                //设置切换动画，从右边进入，左边退出
                getActivity().overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_invite = new Intent(getContext(), InviteActivity.class);
                intent_invite.putExtra("type", 1);
                startActivity(intent_invite);
                break;
            case R.id.rl_quit: // 退出登录
                String username = SPUtils.getInstance().getValue(SPUtils.KEY_USER_NAME, "");
                String token = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
                Log.e(TAG, "onClick: " + username);
                Log.e(TAG, "onClick: " + token);
                mPresenter.getData(ApiConfig.LOGOUT, username, token, "android", LoadConfig.NORMAL);
                break;
        }
    }


    @Override
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);
        switch (intent.getAction()) {
            case RECTIFY_UPDATE_INFO: // 用户信息，修改以后更新
            case PAY_SUCCESS: // 支付成功
            case CHANGE_ORDER_STATE: // 修改订单状态
                mPresenter.getData(ApiConfig.MINEINFO, key, LoadConfig.NORMAL);
                break;
        }
    }
}
