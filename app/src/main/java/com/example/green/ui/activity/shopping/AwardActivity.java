package com.example.green.ui.activity.shopping;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.pay.GetMarketWheelbean;
import com.example.green.bean.pay.ToSurpriseInfo;
import com.example.green.config.ApiConfig;
import com.example.green.customs.LuckyView;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.ShopModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AwardActivity extends BaseMvpActivity<CommonPresenter, ShopModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.draw_rule)
    TextView mDrawRule;
    @BindView(R.id.lucky_view)
    LuckyView mLuckyView;
    private String key;
    private String marketmanage_id = "2";
    private String pay_sn;
    private List<String> pic = new ArrayList<>();
    private static final String TAG = "AwardActivity";
    private ArrayList<Bitmap> bitmaps;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        pay_sn = getIntent().getStringExtra("pay_sn");

        bitmaps = new ArrayList<>();

    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.DRAW_AWARD, key, marketmanage_id, pay_sn);
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
        return R.layout.activity_award;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.DRAW_AWARD:
                GetMarketWheelbean getMarketWheelbean = (GetMarketWheelbean) t[0];
                if (null != getMarketWheelbean && getMarketWheelbean.getCode().equals("200")) {
                    // 抽奖
                    mPresenter.getData(ApiConfig.STAR_DRAW, key, marketmanage_id);


                    List<GetMarketWheelbean.ResultBean.MarketmanageawardListBean>
                            marketmanageaward_list = getMarketWheelbean.getResult().getMarketmanageaward_list();

                    pic.add(0, marketmanageaward_list.get(0).getMarketmanageaward_picture());
                    pic.add(1, marketmanageaward_list.get(1).getMarketmanageaward_picture());
                    pic.add(2, marketmanageaward_list.get(2).getMarketmanageaward_picture());
                    pic.add(3, marketmanageaward_list.get(3).getMarketmanageaward_picture());
                    pic.add(4, marketmanageaward_list.get(4).getMarketmanageaward_picture());
                    pic.add(5, marketmanageaward_list.get(5).getMarketmanageaward_picture());
                    pic.add(6, marketmanageaward_list.get(6).getMarketmanageaward_picture());
                    pic.add(7, marketmanageaward_list.get(7).getMarketmanageaward_picture());

                    pic.add(8, getMarketWheelbean.getResult().getMarketmanage_info().getBotton());
                    for (String url : pic) {
                        Glide.with(this).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                bitmaps.add(resource);
                                if (bitmaps.size() == pic.size()) {
                                    mLuckyView.setLuckyPrizes(bitmaps);
                                }
                            }
                        });

                        returnBitmap(url, new OnBitmapCallListener() {
                            @Override
                            public void onBitmapSuccess(Bitmap bitmap) {
//                    bitmaps.add(bitmap);
//                    if(bitmaps.size()==list.size()){
//                       luckyView.setLuckyPrizes(bitmaps);
//                    }
                            }
                        });
                    }
                } else {
                    showConfirmDialog(getMarketWheelbean.getMessage());
                }
                break;
            case ApiConfig.STAR_DRAW:
                final ToSurpriseInfo toSurpriseInfo = (ToSurpriseInfo) t[0];
                if (null != toSurpriseInfo && toSurpriseInfo.getCode().equals("200")) {
                    ToSurpriseInfo.ResultBean.DrawInfoBean
                            draw_info = toSurpriseInfo.getResult().getDraw_info();
                    final String sort = draw_info.getSort(); // 动画结束位置

                    mLuckyView.setLuckAnimationEndListener(new LuckyView.OnLuckAnimationEndListener() {
                        @Override
                        public void onLuckAnimationEnd(int pos, String msg) {// msg 传回的 自定义LuckyView mPrizeDescription[]提示信息

                            showConfirmDialog(toSurpriseInfo.getResult().getAwardMsg());
                        }

                        @Override
                        public void onClickLuck() {
                            mLuckyView.setSelectId(Integer.valueOf(sort));
                        }
                    });

                } else {
                    toastActivity(toSurpriseInfo.getMessage());
                }
                break;
        }
    }

    private void showConfirmDialog(String msg) {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }
    // 抽奖规则
    private void showRuleDialog() {
        View view = View.inflate(this, R.layout.dialog_draw_rule, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
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

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    @OnClick({R.id.back, R.id.draw_rule})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                this.finish();
                break;
            case R.id.draw_rule:
                showRuleDialog();
                break;
        }
    }


    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap bitmap;

    private void returnBitmap(final String url, final OnBitmapCallListener onBitmapCallListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    //这是一个一步请求，不能直接返回获取，要不然永远为null
                    //在这里得到BitMap之后记得使用Hanlder或者EventBus传回主线程，不过现在加载图片都是用框架了，很少有转化为Bitmap的需求
                    is.close();
                    if (onBitmapCallListener != null) {
                        onBitmapCallListener.onBitmapSuccess(bitmap);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public interface OnBitmapCallListener {
        void onBitmapSuccess(Bitmap bitmap);
    }
}
