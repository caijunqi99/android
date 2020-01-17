package com.example.green.ui.activity.shopping;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.store.MyRandomGoodsAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.store.RandomRecListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.ShopModel;
import com.example.green.ui.activity.GoodsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentSuccessActivity extends BaseMvpActivity<CommonPresenter, ShopModel>
        implements ICommonView {

    @BindView(R.id.tv_down)
    TextView mTvDown;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.pay_price)
    TextView mPayPrice;
    @BindView(R.id.draw_award)
    Button mDraw_award;
    @BindView(R.id.discounts_price)
    TextView mDiscountsPrice;
    @BindView(R.id.recommend_recycler)
    RecyclerView mRecommendRecycler;
    private MyRandomGoodsAdapter mMyRecommendGoodsItemAdapter;
    private List<RandomRecListbean.ResultBean> mRecommend;
    private static final String TAG = "PaymentSuccessActivity";
    private String pay_sn;
    private int TYPE;
    private int draw;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        final Intent intent = getIntent();
        TYPE = intent.getIntExtra("type", 0);
        draw = intent.getIntExtra("draw", 0); // 抽奖 0抽奖关闭，1抽奖开启
        if (draw == 1) {
            mDraw_award.setVisibility(View.VISIBLE);
        } else {
            mDraw_award.setVisibility(View.GONE);
        }
        String payment = intent.getStringExtra("payment");
        pay_sn = intent.getStringExtra("pay_sn");
        mPayPrice.setText(Html.fromHtml("&yen;") + payment);
        mRecommend = new ArrayList<>();
        mMyRecommendGoodsItemAdapter = new MyRandomGoodsAdapter(R.layout.layout_recommendgoods_item, mRecommend);
        mRecommendRecycler.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        mRecommendRecycler.setAdapter(mMyRecommendGoodsItemAdapter);
        mMyRecommendGoodsItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.goods_card:
                        int goods_id = mRecommend.get(position).getGoods_id();
                        Intent intent_detail = new Intent(PaymentSuccessActivity.this, GoodsDetailsActivity.class);
                        intent_detail.putExtra("goodsId", goods_id + "");
                        //设置切换动画，从右边进入，左边退出
                        overridePendingTransition(R.anim.in_from_right,
                                R.anim.out_to_left);
                        startActivity(intent_detail);
                        break;
                }
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.RANDOM_GOODS, LoadConfig.NORMAL);
    }

    @Override
    protected ShopModel initModel() {
        return new ShopModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment_success;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.RANDOM_GOODS:
                RandomRecListbean randomRecListbean = (RandomRecListbean) t[0];
                if (null != randomRecListbean && randomRecListbean.getCode().equals("200")) {
                    List<RandomRecListbean.ResultBean> result = randomRecListbean.getResult();
                    mRecommend.addAll(result);
                    mMyRecommendGoodsItemAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @OnClick({R.id.tv_down, R.id.draw_award})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_down:
                if (TYPE == 1) {
                    Intent intent = new Intent();
                    intent.setAction("con.lcry.close.AffirmActivity");
                    sendBroadcast(intent);
                }
                finish();
                break;
            case R.id.draw_award:
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                Intent intent_draw = new Intent(PaymentSuccessActivity.this, AwardActivity.class);
                intent_draw.putExtra("pay_sn", pay_sn);
                startActivity(intent_draw);
                break;
        }
    }
}
