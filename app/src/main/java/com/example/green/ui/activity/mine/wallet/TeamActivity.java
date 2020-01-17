package com.example.green.ui.activity.mine.wallet;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.adapter.mine.TeamAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.TeamBean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.zhitui_num)
    TextView mTvZhitui;
    @BindView(R.id.team_num)
    TextView mTvTeam;
    @BindView(R.id.rl_invite)
    RelativeLayout mRlInvite;
    @BindView(R.id.recycler_record)
    RecyclerView mRecyclerRecord;
    private List<TeamBean.ResultBean.DatainfoBean> mTeamList;
    private TeamAdapter mTeamAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTeamList = new ArrayList<>();
        mTeamAdapter = new TeamAdapter(R.layout.item_team, mTeamList);
        mRecyclerRecord.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerRecord.setAdapter(mTeamAdapter);

    }

    @Override
    protected void initData() {
        String token = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mPresenter.getData(ApiConfig.USER_TEAM, token, LoadConfig.NORMAL);
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
        return R.layout.activity_team;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.USER_TEAM:
                TeamBean mTeamBeans = (TeamBean) t[0];
                if (null != mTeamBeans && mTeamBeans.getCode() == 200) {
                    mTeamList.addAll(mTeamBeans.getResult().getDatainfo());
                    mTvZhitui.setText(mTeamBeans.getResult().getCountOne() + "");
                    mTvTeam.setText(mTeamBeans.getResult().getCountAll() + "");
                    mTeamAdapter.notifyDataSetChanged();
                } else {
                    toastActivity(mTeamBeans.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.rl_invite})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_invite:
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent mInviteIntent = new Intent(this, InviteActivity.class);
                startActivity(mInviteIntent);
                break;
        }
    }
}
