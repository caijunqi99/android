package com.example.green.customs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.green.bean.pay.GetMarketWheelbean;

import java.util.ArrayList;
import java.util.List;

public class LuckyView extends View {
    private Paint mPaint;
    private float mStrokeWidth = 5;
    private int mRepeatCount = 5; // 转的圈数
    private int mRectSize; // 矩形的宽和高（矩形为正方形）
    private boolean mShouldStartFlag;
    private boolean mShouldStartNextTurn = true; // 标记是否应该开启下一轮抽奖
    private int mStartLuckPosition = 0; // 开始抽奖的位置
    private int mCurrentPosition = -1; // 当前转圈所在的位置

    private OnLuckAnimationEndListener mLuckAnimationEndListener;

    /**
     * 可以通过对 mLuckNum 设置计算策略，来控制用户 中哪些奖 以及 中大奖 的概率
     */
    private int mLuckNum = 3; // 默认最终中奖位置

    private List<RectF> mRectFs; // 存储矩形的集合
    private int[] mItemColor = {Color.parseColor("#ffefd6"), Color.parseColor("#ffefd6")}; // 矩形的颜色
    private String[] mPrizeDescription = {"满20减1元券", "满10减1元券", "满30减2元券", "满5减1元券", "免单", "满300减40元券", "满100减10元券", "满500减50元券", "开始"};
    private List<Bitmap> mLuckyPrizes = new ArrayList<>();
    private List<String> lettersBeans;
    private float left;
    private float top;
    private Bitmap[] bitmaps;
    private String[] luckyName;
    private String[] id = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private int selectPos;
    private GetMarketWheelbean mGetMarketWheelbean;
    private static final String TAG = "LuckyView";

    public LuckyView(Context context) {
        this(context, null);
    }

    public LuckyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAwardInfo(GetMarketWheelbean pGetMarketWheelbean) {
        this.mGetMarketWheelbean = pGetMarketWheelbean;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 抗锯齿
        mPaint.setStyle(Paint.Style.FILL);
        // mPaint.setStyle(Paint.Style.STROKE); // 设置样式为描边
        mPaint.setStrokeWidth(mStrokeWidth); // 设置描边的宽度

        mRectFs = new ArrayList<>();
    }

    public void setLuckAnimationEndListener(OnLuckAnimationEndListener luckAnimationEndListener) {
        mLuckAnimationEndListener = luckAnimationEndListener;
    }

    public int getLuckNum() {
        return mLuckNum;
    }

    public void setLuckNum(int luckNum) {
        mLuckNum = luckNum;
    }

    public List<Bitmap> getLuckyPrizes() {
        return mLuckyPrizes;
    }

    public void setLuckyPrizes(ArrayList<Bitmap> luckyPrizes) {
        mLuckyPrizes = luckyPrizes;
        if (mLuckyPrizes.size() == 9) {
            invalidate();
        }
    }

    public void setData(List<String> lettersBeans) {
        this.lettersBeans = lettersBeans;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectSize = (Math.min(w, h)) / 3; // 矩形的宽高

        mRectFs.clear(); // 当控件大小改变的时候清空数据
        initNineRect();
    }

    /**
     * 初始化 9 个矩形（正方形）的位置信息
     */
    private void initNineRect() {
        final float width = getWidth();

        // 加载前三个矩形
        for (int i = 0; i < 3; i++) {
            float left = i * mRectSize + 5;
            float right = (i + 1) * mRectSize;
            float top = 5;
            float bottom = mRectSize;
            RectF rectF = new RectF(left, top, right, bottom);
            mRectFs.add(rectF);
        }

        // 加载第 4 个矩形
        mRectFs.add(new RectF(width - mRectSize + 5, mRectSize + 5, width, 2 * mRectSize));

        // 加载第 5~7 个矩形
        for (int j = 3; j > 0; j--) {
            float left = width - (4 - j) * mRectSize + 5;
            float right = width - (3 - j) * mRectSize;
            float top = 2 * mRectSize + 5;
            float bottom = 3 * mRectSize;
            RectF rectF = new RectF(left, top, right, bottom);
            mRectFs.add(rectF);
        }

        // 加载第 8 个矩形
        mRectFs.add(new RectF(5, mRectSize + 5, mRectSize, 2 * mRectSize));

        // 加载中心第 9 个矩形
        mRectFs.add(new RectF(mRectSize + 5, mRectSize + 5, 2 * mRectSize, 2 * mRectSize));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNineRect(canvas);
        drawNineBitmaps(canvas); // 填充奖品图片
//        drawNineText(canvas); // 填充奖品文字
    }

    /**
     * 在每个矩形中填充奖品图片
     * left：The position of the left side of the bitmap being drawn
     * top：The position of the top side of the bitmap being drawn
     */
    private void drawNineBitmaps(final Canvas canvas) {

        for (int i = 0; i < mRectFs.size(); i++) {
            RectF rectF = mRectFs.get(i);
            // 将图片设置在每个矩形中央
            left = rectF.left + mRectSize / 4;
            top = rectF.top + mRectSize / 4;
            Log.e(TAG, "drawNineBitmaps: " + mLuckyPrizes.toString());
            if (mLuckyPrizes != null && mLuckyPrizes.size() > 0) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(mLuckyPrizes.get(i), mRectSize / 2, mRectSize / 2, false), left, top, null);
            }
        }
    }


    /**
     * 在每个矩形中央填充文字，代替抽奖图片
     * x：he x-coordinate of the origin of the text being drawn
     * y：The y-coordinate of the baseline of the text being drawn
     */
    private void drawNineText(Canvas canvas) {
        for (int i = 0; i < mRectFs.size(); i++) {
            RectF rectF = mRectFs.get(i);
            float x = rectF.left + mRectSize / 4; // 将文字设置在每个矩形中央
            float y = rectF.top + mRectSize - 20;
            mPaint.setColor(Color.parseColor("#5e5448"));
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(15); // unit px
            if (i == mRectFs.size() - 1) {
                canvas.drawText("", x, y, mPaint);
            } else {
                canvas.drawText(mPrizeDescription[i], x, y, mPaint);
            }
        }
    }

    /**
     * 执行真正的绘制矩形操作
     */
    private void drawNineRect(Canvas canvas) {
        for (int x = 0; x < mRectFs.size(); x++) {
            RectF rectF = mRectFs.get(x);
            if (x == 8) {
                mPaint.setColor(Color.WHITE);
            } else {
                if (mCurrentPosition == x) {
                    mPaint.setColor(Color.parseColor("#edcea9"));
                } else {
                    mPaint.setColor(mItemColor[x % 2]); // 标记当前转盘经过的位置
                }
            }
            canvas.drawRect(rectF, mPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mShouldStartFlag = mRectFs.get(8).contains(event.getX(), event.getY());
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mShouldStartFlag) {
                if (mRectFs.get(8).contains(event.getX(), event.getY())) {
                    mLuckAnimationEndListener.onClickLuck();
                    startAnim(); // 判断只有手指落下和抬起都在中间的矩形内时才开始执行动画抽奖
                }
                mShouldStartFlag = false;
            }
        }
        return super.onTouchEvent(event);
    }

    private void startAnim() {
        if (!mShouldStartNextTurn) {
            return;
        }
        setLuckNum(selectPos); // 设置选中的奖品的位置

        ValueAnimator animator = ValueAnimator.ofInt(mStartLuckPosition, mRepeatCount * 8 + selectPos)
                .setDuration(5000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final int position = (int) animation.getAnimatedValue();
                setCurrentPosition(position % 8);
                mShouldStartNextTurn = false;
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 抽奖结束  设置下轮抽奖不开启
                mShouldStartNextTurn = false;
//                mStartLuckPosition = mLuckNum;
                mStartLuckPosition = selectPos; // 上次中奖位置（动画结束）
                //最终选中的位置
                if (mLuckAnimationEndListener != null) {
                    mLuckAnimationEndListener.onLuckAnimationEnd(mCurrentPosition,
                            mPrizeDescription[mCurrentPosition]);
                }
            }
        });

        animator.start();
    }

    private void setCurrentPosition(int position) {
        mCurrentPosition = position;
        invalidate(); // 强制刷新，在 UI 线程回调 onDraw()
    }

    public void setBitmap(Bitmap[] bitmaps1, String[] name, String[] strings) {
        bitmaps = bitmaps1;
        luckyName = name;
        id = strings;
        invalidate();
    }

    /**
     * 选中id
     *
     * @param datas
     */
    public void setSelectId(int datas) {
        String selectId = datas + "";

        if (id != null && id.length != 0) {
            for (int i = 0; i < id.length; i++) {
                if (id[i].equals(selectId)) {
                    selectPos = i;
                }
            }
        }

        startAnim();
    }

    /**
     * 用于抽奖结果回调
     */
    public interface OnLuckAnimationEndListener {
        void onLuckAnimationEnd(int pos, String msg);

        void onClickLuck();
    }
}
