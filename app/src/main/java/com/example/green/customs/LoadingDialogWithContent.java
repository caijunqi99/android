
package com.example.green.customs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.green.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * 带提示内容的进度框
 */
public class LoadingDialogWithContent extends Dialog {

    //    ProgressImageView mProgressView;
    GifImageView mGifImageView;
    TextView mTvContent;
    String mContent;

    public LoadingDialogWithContent(Context context, String content) {
        super(context, R.style.DialogStyle);
        mContent = content;
    }

    public LoadingDialogWithContent(Context context, boolean cancelable, String content) {
        super(context, R.style.DialogStyle);
        setCancelable(cancelable);
        mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_content);
        mGifImageView = findViewById(R.id.view_list_empty_progress);
        mTvContent = findViewById(R.id.tv_content);
        mTvContent.setText(mContent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mGifImageView != null)
            mGifImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mGifImageView != null)
            mGifImageView.setVisibility(View.GONE);
    }
}
