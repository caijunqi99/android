package com.example.green.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.green.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyBottomView extends LinearLayout {
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    public static final int FIFTH = 5;
    @BindView(R.id.image_first)
    ImageView imageFirst;
    @BindView(R.id.text_first)
    TextView textFirst;
    @BindView(R.id.rl_first)
    RelativeLayout rlFirst;
    @BindView(R.id.image_second)
    ImageView imageSecond;
    @BindView(R.id.text_second)
    TextView textSecond;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.image_third)
    ImageView imageThird;
    @BindView(R.id.text_third)
    TextView textThird;
    @BindView(R.id.rl_third)
    RelativeLayout rlThird;
    @BindView(R.id.image_fourth)
    ImageView imageFourth;
    @BindView(R.id.text_fourth)
    TextView textFourth;
    @BindView(R.id.rl_fourth)
    RelativeLayout rlFourth;
    @BindView(R.id.image_fifth)
    ImageView imageFifth;
    @BindView(R.id.text_fifth)
    TextView textFifth;
    @BindView(R.id.rl_fifth)
    RelativeLayout rlFifth;
    @BindView(R.id.bottom_ll)
    LinearLayout bottom;
    private boolean mChangeDrawable;
    private boolean mChangeTextColor;
    private int mTextSelectedColor;
    private int mTextUnSelectedColor;
    private float mTextSize;
    private int mImageSelectedFifth;
    private int mImageSelectedFourth;
    private int mImageSelectedThird;
    private int mImageSelectedSecond;
    private int mImageSelectedFirst;
    private int mImageUnSelectedFifth;
    private int mImageUnSelectedFourth;
    private int mImageUnSelectedThird;
    private int mImageUnSelectedSecond;
    private int mImageUnSelectedFirst;
    private String mTextFirst;
    private String mTextSecond;
    private String mTextThird;
    private String mTextFourth;
    private String mTextFifth;
    private Unbinder mBind;
    private int mBottomNum;

    public MyBottomView(Context context) {
        super(context);
    }

    public MyBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Bottom_tab, 0, 0);
        initAttrs(context, typedArray);
        initView();
    }

    public void setBottomBg(int color) {
        bottom.setBackgroundColor(color);
    }

    public void setBottomNum(int pBottomNum) {
        if (pBottomNum == 4) {
            rlFifth.setVisibility(GONE);
        } else if (pBottomNum == 3) {
            rlFifth.setVisibility(GONE);
            rlFourth.setVisibility(GONE);
        } else if (pBottomNum == 2) {
            rlFifth.setVisibility(GONE);
            rlThird.setVisibility(GONE);
            rlFourth.setVisibility(GONE);
        }
    }

    public void setBottomTextSize(Context context, float pTextSize) {
        if (pTextSize == 0.0) pTextSize = mTextSize;
        textFirst.setTextSize(pTextSize);
        textSecond.setTextSize(pTextSize);
        textThird.setTextSize(pTextSize);
        textFourth.setTextSize(pTextSize);
        textFifth.setTextSize(pTextSize);
    }

    public void setFourthSelected() {
        if (mChangeDrawable) {
            setUpImage();
            imageFourth.setImageResource(mImageSelectedFourth);
        }
        if (mChangeTextColor) {
            setUpTextColor();
            textFourth.setTextColor(mTextSelectedColor);
        }
        mBottomClick.onFourthClick();
    }

    private void initAttrs(Context context, TypedArray pTypedArray) {
        try {
            mChangeDrawable = pTypedArray.getBoolean(R.styleable.Bottom_tab_changeDrawable, false);
            mBottomNum = pTypedArray.getInteger(R.styleable.Bottom_tab_bottomNum, 5);
            mChangeTextColor = pTypedArray.getBoolean(R.styleable.Bottom_tab_changeTextColor, true);
            mTextSelectedColor = pTypedArray.getColor(R.styleable.Bottom_tab_textSelectedColor, ContextCompat.getColor(context, R.color.app_theme_color));
            mTextUnSelectedColor = pTypedArray.getColor(R.styleable.Bottom_tab_textUnSelectedColor, Color.BLACK);
            mTextSize = pTypedArray.getDimension(R.styleable.Bottom_tab_bottomTextSize, getResources().getDimension(R.dimen.sp_3));
            mImageSelectedFifth = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageSelectedFifth, 0);
            mImageSelectedFourth = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageSelectedFourth, 0);
            mImageSelectedThird = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageSelectedThird, 0);
            mImageSelectedSecond = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageSelectedSecond, 0);
            mImageSelectedFirst = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageSelectedFirst, 0);
            mImageUnSelectedFifth = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageUnSelectedFifth, 0);
            mImageUnSelectedFourth = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageUnSelectedFourth, 0);
            mImageUnSelectedThird = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageUnSelectedThird, 0);
            mImageUnSelectedSecond = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageUnSelectedSecond, 0);
            mImageUnSelectedFirst = pTypedArray.getResourceId(R.styleable.Bottom_tab_imageUnSelectedFirst, 0);
            mTextFirst = pTypedArray.getString(R.styleable.Bottom_tab_textFirst);
            mTextSecond = pTypedArray.getString(R.styleable.Bottom_tab_textSecond);
            mTextThird = pTypedArray.getString(R.styleable.Bottom_tab_textThird);
            mTextFourth = pTypedArray.getString(R.styleable.Bottom_tab_textFourth);
            mTextFifth = pTypedArray.getString(R.styleable.Bottom_tab_textFifth);
        } finally {
            pTypedArray.recycle();
        }

    }

    public MyBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_view, this, true);
        mBind = ButterKnife.bind(this, inflate);

        setUpImage();
        setUpTextColor();
        setUpTextContent();
        textFirst.setTextColor(mTextSelectedColor);
        imageFirst.setImageResource(mImageSelectedFirst);
        if (mBottomNum == 4) {
            rlFifth.setVisibility(GONE);
        } else if (mBottomNum == 3) {
            rlFifth.setVisibility(GONE);
            rlFourth.setVisibility(GONE);
        } else if (mBottomNum == 2) {
            rlFifth.setVisibility(GONE);
            rlThird.setVisibility(GONE);
            rlFourth.setVisibility(GONE);
        }
    }

    private void setUpTextColor() {
        textFifth.setTextColor(mTextUnSelectedColor);
        textFourth.setTextColor(mTextUnSelectedColor);
        textThird.setTextColor(mTextUnSelectedColor);
        textSecond.setTextColor(mTextUnSelectedColor);
        textFirst.setTextColor(mTextUnSelectedColor);
    }

    private void setUpTextContent() {
        textThird.setText(mTextThird);
        textFourth.setText(mTextFourth);
        textFifth.setText(mTextFifth);
        textSecond.setText(mTextSecond);
        textFirst.setText(mTextFirst);
    }

    private void setUpImage() {
        imageFifth.setImageResource(mImageUnSelectedFifth);
        imageFourth.setImageResource(mImageUnSelectedFourth);
        imageThird.setImageResource(mImageUnSelectedThird);
        imageSecond.setImageResource(mImageUnSelectedSecond);
        imageFirst.setImageResource(mImageUnSelectedFirst);
    }

    @OnClick({R.id.rl_first, R.id.rl_second, R.id.rl_third, R.id.rl_fourth, R.id.rl_fifth})
    public void onViewClicked(View view) {
        if (mBottomClick == null) return;
        switch (view.getId()) {
            case R.id.rl_first:
                if (mChangeDrawable) {
                    setUpImage();
                    imageFirst.setImageResource(mImageSelectedFirst);
                }
                if (mChangeTextColor) {
                    setUpTextColor();
                    textFirst.setTextColor(mTextSelectedColor);
                }
                mBottomClick.onFirstClick();
                break;
            case R.id.rl_second:
                if (mChangeDrawable) {
                    setUpImage();
                    imageSecond.setImageResource(mImageSelectedSecond);
                }
                if (mChangeTextColor) {
                    setUpTextColor();
                    textSecond.setTextColor(mTextSelectedColor);
                }
                mBottomClick.onSecondClick();
                break;
            case R.id.rl_third:
                if (mChangeDrawable) {
                    setUpImage();
                    imageThird.setImageResource(mImageSelectedThird);
                }
                if (mChangeTextColor) {
                    setUpTextColor();
                    textThird.setTextColor(mTextSelectedColor);
                }
                mBottomClick.onThirdClick();
                break;
            case R.id.rl_fourth:
                if (mChangeDrawable) {
                    setUpImage();
                    imageFourth.setImageResource(mImageSelectedFourth);
                }
                if (mChangeTextColor) {
                    setUpTextColor();
                    textFourth.setTextColor(mTextSelectedColor);
                }
                mBottomClick.onFourthClick();
                break;
            case R.id.rl_fifth:
                if (mChangeDrawable) {
                    setUpImage();
                    imageFifth.setImageResource(mImageSelectedFifth);
                }
                if (mChangeTextColor) {
                    setUpTextColor();
                    textFifth.setTextColor(mTextSelectedColor);
                }
                mBottomClick.onFifthClick();
                break;
        }
    }

    public OnBottomClick mBottomClick;

    public void setOnBottomClickListener(OnBottomClick pOnBottomClick) {
        this.mBottomClick = pOnBottomClick;
    }

    public interface OnBottomClick {
        void onFirstClick();

        void onSecondClick();

        void onThirdClick();

        void onFourthClick();

        void onFifthClick();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBind.unbind();
    }
}
