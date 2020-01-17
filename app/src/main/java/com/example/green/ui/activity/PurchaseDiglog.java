package com.example.green.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.green.R;
import com.example.green.bean.homepage.DetailsDatabean;
import com.yiyatech.utils.ext.ToastUtils;

import androidx.annotation.NonNull;


public class PurchaseDiglog extends Dialog {

    private int num = 1;
    private int type;
    @NonNull
    private final Context context;
    private final DetailsDatabean.ResultBean mResult;

    public PurchaseDiglog(@NonNull @android.support.annotation.NonNull Context context, DetailsDatabean.ResultBean mResult, int type) {
        super(context);
        this.context = context;
        this.mResult = mResult;
        this.type = type;
        // 立即购买
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pop_purchase, null);

        initView(view);

        setContentView(view);
    }

    private void initView(View view) {
        ImageView goods_iv = view.findViewById(R.id.iv_goods);
        TextView price = view.findViewById(R.id.tv_price);
        TextView info = view.findViewById(R.id.tv_info);
        final TextView mNum = view.findViewById(R.id.num);
        RelativeLayout rl_add = view.findViewById(R.id.rl_add);
        RelativeLayout rl_subtract = view.findViewById(R.id.rl_subtract);
        RelativeLayout rl_close = view.findViewById(R.id.rl_close);
        Button bt_buy = view.findViewById(R.id.bt_buy);
        if (type == 0) {
            bt_buy.setText("立即购买");
        } else {
            bt_buy.setText("加入购物车");
        }

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        price.setText(mResult.getGoods_info().getGoods_price());
        info.setText(mResult.getGoods_info().getGoods_name());
        mNum.setText(String.valueOf(num));
        rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + 1;
                mNum.setText(String.valueOf(num));
            }
        });

        rl_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = mNum.getText().toString().trim();
                Integer integer = Integer.valueOf(trim);
                if (integer > 1) {
                    num = num - 1;
                } else {
                    ToastUtils.show(context, "商品不能再减少了");
                }
                mNum.setText(String.valueOf(num));
            }
        });

        Glide.with(context).load(mResult.getGoods_image().get(0)).into(goods_iv);

        bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(num, mResult);
            }
        });

    }

    @Override
    public void show() {
        if (getWindow() != null) {
            this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
        super.show();
        fullScreenImmersive(getWindow().getDecorView());
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        if (layoutParams != null) {
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
            getWindow().setAttributes(layoutParams);
        }
    }

    public void fullScreenImmersive(View view) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                view.setSystemUiVisibility(uiOptions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 接口回调
     */
    OnItemClickListener onItemClickListener; //ma

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {// onItemClickListener外界传进来 ma
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int num, DetailsDatabean.ResultBean mResult);
    }
}
