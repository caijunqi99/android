package com.example.green.customs.banner;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.bean.homepage.DetailsDatabean;
import com.example.green.local_utils.DipDpUtil;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.yiyatech.utils.ext.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjj on 18/12/20 20:40
 * 1376881525@qq.com
 * 自定义无限轮播banner控件
 */
public class BannerView extends RelativeLayout implements BannerAdapter.ViewPagerOnItemClickListener {
    ViewPager viewPager;
    LinearLayout points;
    //默认轮播时间，3s
    private int delayTime = 3000;
    private List<ImageView> imageViewList;
    private List<String> bannerList;

    //选中显示Indicator 小圆点样式
    private int selectRes = R.drawable.shape_dots_select;
    //条形指示器样式
    //    private int selectRes = R.drawable.shape_line_select;
    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;
    //    private int unSelcetRes = R.drawable.shape_line_default;
    //当前页的下标
    private int currentPos;

    private Handler handler;
    private static final String TAG = "BannerView";

    public ViewPager getViewPager() {
        return viewPager;
    }

    public BannerView(Context context) {
        this(context, null);
        initView();
    }


    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_banner, this, true);
        imageViewList = new ArrayList<>();
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.layout_banner_viewpager);
        points = findViewById(R.id.layout_banner_points_group);
    }


    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {
        this.delayTime = time;
        return this;
    }


    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {
        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }


    /**
     * 图片轮播需要传入参数
     */
    public void build(DetailsDatabean.ResultBean pResultBean) {
        destroy();
        List<String> goods_image = pResultBean.getGoods_image();
        if (goods_image.size() == 0) {
            this.setVisibility(GONE);
            return;
        }
        bannerList = new ArrayList<>();
        bannerList.addAll(goods_image);
        final int pointSize;
        pointSize = bannerList.size();
        if (pointSize == 2) {
            bannerList.addAll(goods_image);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//          DipDpUtil.dip2px(getContext(), 5),
//              DipDpUtil.dip2px(getContext(), 5));
                    DipDpUtil.dip2px(getContext(), 13),
                    DipDpUtil.dip2px(getContext(), 2));
            params.leftMargin = DipDpUtil.dip2px(getContext(), 4);
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }
        points.getChildAt(0).setBackgroundResource(selectRes);
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView mImageView = new ImageView(getContext());
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            mImageView.setLayoutParams(p);
            if (!StringUtils.isBlank(bannerList.get(i))) {
                mImageView.setImageResource(R.drawable.ic_launcher_background);
            } else {

                Glide.with(getContext())
                        .load(bannerList.get(i))
                        .apply(RequestOptions
                                .placeholderOf(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background)
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(mImageView);
                Log.d("bannerview", "--url" + bannerList.get(i));
            }

            imageViewList.add(mImageView);
        }
        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                pos = pos % pointSize;
                currentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    // 闲置中
                    case ViewPager.SCROLL_STATE_IDLE:
                        isAutoPlay = true;
                        break;
                    // 拖动中
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isAutoPlay = false;
                        break;
                    // 设置中
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isAutoPlay = true;
                        break;
                }
            }
        });

        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);
        //图片开始轮播
        startScroll();
    }

    private boolean isAutoPlay = false;


    /**
     * 图片开始轮播
     */
    private void startScroll() {
        // 如果少于2张就不用自动播放了
        if (bannerList.size() < 2) {
            isAutoPlay = false;
        } else {
            isAutoPlay = true;
            handler = new Handler();
            handler.postDelayed(task, delayTime);
        }
    }


    private Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                // 正常每隔3秒播放一张图片
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//        vpImageTitle.setCurrentItem(currentItem);
                handler.postDelayed(task, 3000);
            } else {
                // 如果处于拖拽状态停止自动播放，会每隔5秒检查一次是否可以正常自动播放。
                handler.postDelayed(task, 5000);
            }
        }
    };


    public void destroy() {
    }


    /**
     * 设置ViewPager的Item点击回调事件
     */
//  @Override
//  public void onItemClick(int positon) {

//    BrowserActivity.launch((Activity) getContext(),
//        bannerList.get(currentPos).link,
//        bannerList.get(currentPos).title);

//  }


    /**
     * 设置ViewPager的Item点击回调事件
     */
    @Override
    public void onBannerItemClick(int possition) {

        Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
        intent.putExtra("isVideo", false);
        intent.putExtra("category", "new");
        getContext().startActivity(intent);

        //如果有特殊需求可以从外面回调点击事件接口
//    MainActivity.banneritemlistener.onBannerItemClick(possition);
    }
}