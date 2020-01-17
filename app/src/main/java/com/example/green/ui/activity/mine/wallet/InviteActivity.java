package com.example.green.ui.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;

public class InviteActivity extends AppCompatActivity {

    private WebView mIntiveWeb;
    private ImageView mBack;
    private TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        mBack = findViewById(R.id.back);
        Title = findViewById(R.id.tv_title);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                finish();
            }
        });

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        if (type == 1) {
            Title.setText("推广海报");
        }
        String mToken = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mIntiveWeb = findViewById(R.id.intiveWeb);
        mIntiveWeb.getSettings().setJavaScriptEnabled(true);
        mIntiveWeb.setWebViewClient(new WebViewClient());
        mIntiveWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                // 使用自定义 dialog
                showConfirmDialog(result);
                return true; // (拦截js alert转为原生dialog)
            }
        });
        mIntiveWeb.loadUrl("https://shop.bayi-shop.com/wap/tmpl/member/member_inviter_poster.html?key=" + mToken);

    }


    private void showConfirmDialog(final JsResult pJsResult) {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText("复制成功");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                /**解决alert只弹出一次的问题*/
                pJsResult.cancel();
            }
        });
    }
}
