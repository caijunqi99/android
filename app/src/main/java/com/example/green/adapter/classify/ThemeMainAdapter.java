package com.example.green.adapter.classify;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.green.R;
import com.example.green.bean.classify.AllClassifyListbean;

import java.util.List;

public class ThemeMainAdapter extends RecyclerView.Adapter<ThemeMainAdapter.ViewHolder> {
    private Context mContext;
    private List<AllClassifyListbean.ResultBean.ClassListBean> listinfos;

    public ThemeMainAdapter(Context context) {
        this.mContext = context;
    }

    //接收数据
    public void setData(List<AllClassifyListbean.ResultBean.ClassListBean> listinfos) {
        this.listinfos = listinfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_theme_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.itemView.setId(position);

        AllClassifyListbean.ResultBean.ClassListBean classListBean = listinfos.get(position);
        if (null != classListBean) {
            holder.text_content.setText(classListBean.getGc_name());
            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                    .fallback(R.mipmap.ic_launcher) //url为空的时候,显示的图片
                    .error(R.mipmap.ic_launcher)
                    .centerCrop();//
            // 图片加载失败后，显示的图片
            if (null != classListBean.getImage()) {
                Glide.with(mContext).load(classListBean.getImage()).apply(options).into(holder.iv);
            }
        }

        if (classListBean.isChick()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectorListener.onSelect(v, position);
            }
        });

    }

    public interface OnSelectorListener {
        void onSelect(View view, int position);
    }

    public void setOnSelectorListener(OnSelectorListener listener) {
        mSelectorListener = listener;
    }

    private OnSelectorListener mSelectorListener;

    @Override
    public int getItemCount() {
        return listinfos.size() == 0 ? 0 : listinfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_content;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            text_content = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}