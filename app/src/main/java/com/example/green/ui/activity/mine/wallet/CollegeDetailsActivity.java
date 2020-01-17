package com.example.green.ui.activity.mine.wallet;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.CollegeDetailsInfo;
import com.example.green.config.ApiConfig;
import com.example.green.customs.HtmlUtil;
import com.example.green.model.MineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.green.customs.HtmlUtil.getHtmlData;

public class CollegeDetailsActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.content_title)
    TextView title;
    private String articleId;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();
        int article_id = intent.getIntExtra("article_id", 0);
        articleId = String.valueOf(article_id);


        /*解决图片不显示*/
        mWebView.getSettings().setBlockNetworkImage(false);//不阻塞网络图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //允许混合（http，https）
            //websettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受所有网站的证书
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.COLLEGE_DETAILS, articleId);
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
        return R.layout.activity_college_details;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.COLLEGE_DETAILS:
                CollegeDetailsInfo collegeDetailsInfo = (CollegeDetailsInfo) t[0];
                if (null != collegeDetailsInfo && collegeDetailsInfo.getCode().equals("200")) {
                    title.setText(collegeDetailsInfo.getResult().getArticle_title());
                    /**
                     * 将文本HTML显示在webview中
                     */
                    //封装头文件
                    String sHead = "<html><head><meta name=\"viewport\" content=\"width=device-width, " +
                            "initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes\" />" +
                            "<style>img{max-width:100% !important;height:auto !important;}</style>"
                            + "<style>body{max-width:100% !important;}</style>" + "</head><body>";
//                    String body = HtmlUtil.getHtmlData(collegeDetailsInfo.getResult().getArticle_content());

                    mWebView.loadDataWithBaseURL(null, sHead +
                            collegeDetailsInfo.getResult().getArticle_content() + "</body></html>", "text/html", "UTF-8", null);
                }
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
