package com.example.green.ui.fragment.classify;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.adapter.classify.MyRightClassifyAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.classify.AllClassifyListbean;
import com.example.green.bean.classify.RightClassifyListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.ClassifyModel;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightClassifyFragment extends BaseMvpFragment<CommonPresenter, ClassifyModel>
        implements ICommonView {

    @BindView(R.id.right_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.adv_banner)
    Banner mBanner;
    private MyRightClassifyAdapter mMyRightClassifyAdapter;
    private AllClassifyListbean.ResultBean.ClassListBean mClassListBean;
    private List<String> imgs;
    private List<RightClassifyListbean.ResultBean.AdvListBean> mAdv_list;
    private List<RightClassifyListbean.ResultBean.ClassListBean> mClass_list;
    private static final String TAG = "RightClassifyFragment";

    public RightClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected ClassifyModel initModel() {
        return new ClassifyModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_right_classify;
    }

    @Override
    protected void initView() {
        // 分类ID
        mClassListBean = (AllClassifyListbean.ResultBean.ClassListBean) getArguments().getSerializable("gc_id");
        imgs = new ArrayList<>();
        mAdv_list = new ArrayList<>();
        mClass_list = new ArrayList<>();
        mMyRightClassifyAdapter = new MyRightClassifyAdapter(mClass_list, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMyRightClassifyAdapter);
    }

    /*
     * 初始化轮播图
     * */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initBanner() {
        mBanner.setIndicatorGravity(BannerConfig.CENTER);//圆点的位置
        mBanner.setImages(imgs)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .isAutoPlay(true)
                .setDelayTime(2000).start();//图片循环滑动的时间2秒

        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                RequestOptions transform = new RequestOptions().transform(new RoundedCorners(20));
                Glide.with(getContext()).load(path).apply(transform).into(imageView);
            }
        }).start();

        mBanner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
            }
        });
        mBanner.setClipToOutline(true);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (null != mAdv_list.get(position).getGoodscn_adv_link() && !mAdv_list.get(position).getGoodscn_adv_link().equals("")) {
                    Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                    intent.putExtra("goodsId", mAdv_list.get(position).getGoodscn_adv_link());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void initData() {
        if (null != mClassListBean && mClassListBean.getGc_id() > 0)
            mPresenter.getData(ApiConfig.URL_RIGHTCLASSIFY, mClassListBean.getGc_id(), LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.URL_RIGHTCLASSIFY:
                RightClassifyListbean rightClassifyListbeans = (RightClassifyListbean) t[0];
                if (null != rightClassifyListbeans) {
                    mAdv_list.addAll(rightClassifyListbeans.getResult().getAdv_list());
                    mClass_list.addAll(rightClassifyListbeans.getResult().getClass_list());

                    imgs.add(mAdv_list.get(0).getGoodscn_adv());
                    imgs.add(mAdv_list.get(1).getGoodscn_adv());
                    initBanner();
                    mMyRightClassifyAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
