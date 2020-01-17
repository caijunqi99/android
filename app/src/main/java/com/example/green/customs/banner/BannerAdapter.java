package com.example.green.customs.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mList;
    private int pos;
    private ViewPagerOnItemClickListener mViewPagerOnItemClickListener;

    void setmViewPagerOnItemClickListener(ViewPagerOnItemClickListener mViewPagerOnItemClickListener) {
        this.mViewPagerOnItemClickListener = mViewPagerOnItemClickListener;
    }

    BannerAdapter(List<ImageView> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int position1 = position;
        //对ViewPager页号求模取出View列表中要显示的项
        position %= mList.size();
        if (position < 0) {
            position = mList.size() + position;
        }
        ImageView v = mList.get(position);
        pos = position;
        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        v.setLayoutParams(p);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = v.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(v);
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewPagerOnItemClickListener != null) {
//                mViewPagerOnItemClickListener.onBannerItemClick(pos);
                    //发生点击banner的item后position错误异常
                    if (pos == 0) {
                        pos = mList.size() - 1;
                    } else {
                        pos = pos - 1;
                    }
                    mViewPagerOnItemClickListener.onBannerItemClick(pos);
                }
            }
        });
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }


    public interface ViewPagerOnItemClickListener {
        void onBannerItemClick(int possition);
    }
}