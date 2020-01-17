package com.example.green.ui.activity.mine;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.mine.MyCollegeAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.CollegeListbean;
import com.example.green.bean.mine.WalletInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.ui.activity.homepage.LoginActivity;
import com.example.green.ui.activity.mine.wallet.CollegeDetailsActivity;
import com.example.green.ui.activity.mine.wallet.IntegralActivity;
import com.example.green.ui.activity.mine.wallet.InviteActivity;
import com.example.green.ui.activity.mine.wallet.RealNmaeActivity;
import com.example.green.ui.activity.mine.wallet.RechargeActivity;
import com.example.green.ui.activity.mine.wallet.StoredActivity;
import com.example.green.ui.activity.mine.wallet.TeamActivity;
import com.example.green.ui.activity.mine.wallet.TransactionCodeActivity;
import com.example.green.ui.activity.mine.wallet.TransfersActivity;
import com.example.green.ui.activity.mine.wallet.WithdrawActivity;
import com.example.green.ui.fragment.mine.CollegeFragment;
import com.example.green.ui.fragment.mine.VideoFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WalletActivity extends BaseMvpActivity<CommonPresenter, MineModel> implements ICommonView {


    private static int FRAGMENT_COLLEGE = 0;
    private static int FRAGMENT_VIDEO = 1;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.header_iv)
    ImageView mHeaderIv;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.member)
    ImageView mMember;
    @BindView(R.id.recommend_code)
    TextView mRecommendCode;
    @BindView(R.id.user_level)
    TextView mUserLevel;
    @BindView(R.id.rl_user_level)
    RelativeLayout mRlUserLevel;
    @BindView(R.id.company)
    ImageView mCompany;
    @BindView(R.id.user_company)
    TextView mUserCompany;
    @BindView(R.id.rl_user_company)
    RelativeLayout mRlUserCompany;
    @BindView(R.id.chuzhika_balance)
    TextView mChuzhikaBalance;
    @BindView(R.id.usable_integral)
    TextView mUsableIntegral;
    @BindView(R.id.unuse_integral)
    TextView mUnuseIntegral;
    @BindView(R.id.rl_chongzhi)
    RelativeLayout mRlChongzhi;
    @BindView(R.id.rl_tixian)
    RelativeLayout mRlTixian;
    @BindView(R.id.rl_huzhuan)
    RelativeLayout mRlHuzhuan;
    @BindView(R.id.rl_chuzhi)
    RelativeLayout mRlChuzhi;
    @BindView(R.id.rl_jiaoyi)
    RelativeLayout mRlJiaoyi;
    @BindView(R.id.rl_jifen)
    RelativeLayout mRlJifen;
    @BindView(R.id.rl_shiming)
    RelativeLayout mRlShiming;
    @BindView(R.id.rl_team)
    RelativeLayout mRlTeam;
    @BindView(R.id.rl_yaoqing)
    RelativeLayout mRlYaoqing;
    @BindView(R.id.college)
    TextView mCollege;
    /* @BindView(R.id.video)
     TextView mVideo;*/
    @BindView(R.id.tip_1)
    ImageView mTip1;
    /*    @BindView(R.id.tip_2)
        ImageView mTip2;
        @BindView(R.id.fl)
        FrameLayout mFl;*/
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.college_recyclerview)
    RecyclerView mCollegeRecyclerview;

    private CollegeFragment mCollegeFragment;
    private VideoFragment mVideoFragment;
    private String key;
    private WalletInfobean.ResultBean.MemberInfoBean mMember_info;
    private List<CollegeListbean.ResultBean> mResultBeans;
    private MyCollegeAdapter mMyCollegeAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        // 商学院
//        selectFragment(FRAGMENT_COLLEGE);
        mTip1.setVisibility(View.VISIBLE);
//        mTip2.setVisibility(View.GONE);
        mCollege.setTextColor(getResources().getColor(R.color.c_27b28b));

        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mResultBeans = new ArrayList<>();
        mMyCollegeAdapter = new MyCollegeAdapter(R.layout.layout_college_item, mResultBeans);
        mCollegeRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mCollegeRecyclerview.setAdapter(mMyCollegeAdapter);

        mMyCollegeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_college_card:
                        Intent intent = new Intent(WalletActivity.this, CollegeDetailsActivity.class);
                        intent.putExtra("article_id", mResultBeans.get(position).getArticle_id());
                        //设置切换动画，从右边进入，左边退出
                        overridePendingTransition(R.anim.in_from_right,
                                R.anim.out_to_left);
                        startActivity(intent);
                        break;
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getData(ApiConfig.WALLET, key, LoadConfig.REFRESH);
                mPresenter.getData(ApiConfig.COLLEGE, FRAGMENT_COLLEGE, LoadConfig.REFRESH);
            }
        });
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.WALLET, key, LoadConfig.NORMAL);
        mPresenter.getData(ApiConfig.COLLEGE, FRAGMENT_COLLEGE, LoadConfig.NORMAL);
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
        return R.layout.activity_wallet;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.WALLET:
                WalletInfobean walletInfobean = (WalletInfobean) t[0];
                if (null != walletInfobean && walletInfobean.getCode().equals("100")) {
                    ToastUtils.show(this, walletInfobean.getMessage());
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else if (null != walletInfobean && walletInfobean.getCode().equals("200")) {

                    mMember_info = walletInfobean.getResult().getMember_info();
                    int loadmode = (int) t[1];
                    if (loadmode == LoadConfig.NORMAL) {
                        RequestOptions options = new RequestOptions().circleCrop();
                        Glide.with(this).load(mMember_info.getAvator()).apply(options).into(mHeaderIv);
                        mName.setText(mMember_info.getUser_name());
                        mNumber.setText(mMember_info.getMobile());
                        mRecommendCode.setText("推荐码:" + mMember_info.getInviter_code());
                        mUserLevel.setText(mMember_info.getLevel_name());

                        if (mMember_info.getCompany_level().equals("0")) {
                            mRlUserCompany.setVisibility(View.INVISIBLE);
                        } else {
                            mUserCompany.setText(mMember_info.getCompany_level());
                        }

                        if (mMember_info.getLevel_name().equals("普通用户")) {
                            mMember.setImageResource(R.mipmap.no_member);
                        } else {
                            mMember.setImageResource(R.mipmap.member);
                        }
                        mChuzhikaBalance.setText(mMember_info.getAvailable_predeposit());
                        mUsableIntegral.setText(mMember_info.getMember_points_available());
                        mUnuseIntegral.setText(mMember_info.getMember_points());

                    } else if (loadmode == LoadConfig.REFRESH) {
                        RequestOptions options = new RequestOptions().circleCrop();
                        Glide.with(this).load(mMember_info.getAvator()).apply(options).into(mHeaderIv);
                        mName.setText(mMember_info.getUser_name());
                        mNumber.setText(mMember_info.getMobile());
                        mRecommendCode.setText("推荐码:" + mMember_info.getInviter_code());
                        mUserLevel.setText(mMember_info.getLevel_name());

                        if (mMember_info.getCompany_level().equals("0")) {
                            mRlUserCompany.setVisibility(View.INVISIBLE);
                        } else {
                            mUserCompany.setText(mMember_info.getCompany_level());
                        }

                        if (mMember_info.getLevel_name().equals("普通用户")) {
                            mMember.setImageResource(R.mipmap.no_member);
                        } else {
                            mMember.setImageResource(R.mipmap.member);
                        }
                        mChuzhikaBalance.setText(mMember_info.getAvailable_predeposit());
                        mUsableIntegral.setText(mMember_info.getMember_points_available());
                        mUnuseIntegral.setText(mMember_info.getMember_points());
                        mRefreshLayout.finishRefresh();
                    }
                }
                break;
            case ApiConfig.COLLEGE:
                CollegeListbean collegeListbean = (CollegeListbean) t[0];
                if (null != collegeListbean && collegeListbean.getCode().equals("200")) {
                    List<CollegeListbean.ResultBean> result = collegeListbean.getResult();
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mResultBeans.addAll(result);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mResultBeans.clear();
                        mResultBeans.addAll(result);
                        mRefreshLayout.finishRefresh();
                    }

                    mMyCollegeAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.rl_user_level, R.id.rl_user_company, R.id.rl_chongzhi, R.id.rl_tixian,
            R.id.rl_huzhuan, R.id.rl_chuzhi, R.id.rl_jiaoyi, R.id.rl_jifen, R.id.rl_shiming, R.id.rl_team,
            R.id.rl_yaoqing, R.id.rl_college/*, R.id.rl_video*/})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_user_level: // 用户级别
                break;
            case R.id.rl_user_company: // 用户公司
                break;
            case R.id.rl_chongzhi: // 充值
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, RechargeActivity.class));
                break;
            case R.id.rl_tixian: // 提现
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, WithdrawActivity.class));
                break;
            case R.id.rl_huzhuan: // 互转
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, TransfersActivity.class));
                break;
            case R.id.rl_chuzhi: // 储值卡
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, StoredActivity.class));
                break;
            case R.id.rl_jiaoyi: // 认筹股
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, TransactionCodeActivity.class));
                break;
            case R.id.rl_jifen: // 积分
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, IntegralActivity.class));
                break;
            case R.id.rl_shiming: // 实名
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, RealNmaeActivity.class));
                break;
            case R.id.rl_team: // 团队
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WalletActivity.this, TeamActivity.class));
                break;
            case R.id.rl_yaoqing: // 邀请好友
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent mInviteIntent = new Intent(this, InviteActivity.class);
                startActivity(mInviteIntent);
                break;
            case R.id.rl_college: // 商学院
//                selectFragment(FRAGMENT_COLLEGE);
//                mTip1.setVisibility(View.VISIBLE);
//                mTip2.setVisibility(View.GONE);
                mCollege.setTextColor(getResources().getColor(R.color.c_27b28b));
//                mVideo.setTextColor(getResources().getColor(R.color.black));
                break;
            /*case R.id.rl_video: // 视频
                selectFragment(FRAGMENT_VIDEO);
                mTip1.setVisibility(View.GONE);
                mTip2.setVisibility(View.VISIBLE);
                mCollege.setTextColor(getResources().getColor(R.color.black));
                mVideo.setTextColor(getResources().getColor(R.color.c_27b28b));
                break;*/
        }
    }

    public void selectFragment(int position) {//设置传入第几值显示第几个fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (position) {
            case 0:
                if (mCollegeFragment == null) {
                    mCollegeFragment = CollegeFragment.newInstance();
                }
                //将原来的Fragment替换掉---此处R.id.fragmen指的是FrameLayout
                ft.replace(R.id.fl, mCollegeFragment);
                break;
            case 1:
                if (mVideoFragment == null) {
                    mVideoFragment = VideoFragment.newInstance();
                }
                ft.replace(R.id.fl, mVideoFragment);
                break;
            default:
                break;
        }
        ft.commit();
    }

    @Override
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);
        switch (intent.getAction()) {
            case TRANSFORM_SUCCESS: // 用户 互转后 刷新
            case RECHARGE_SUCCESS: // 用户 充值后 刷新
                mPresenter.getData(ApiConfig.WALLET, key, LoadConfig.NORMAL);
                break;
        }
    }
}
