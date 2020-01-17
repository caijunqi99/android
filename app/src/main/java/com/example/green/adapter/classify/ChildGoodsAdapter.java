package com.example.green.adapter.classify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.green.R;
import com.example.green.bean.classify.RightClassifyListbean;

import java.util.List;

public class ChildGoodsAdapter extends RecyclerView.Adapter<ChildGoodsAdapter.ViewHolder> {

    private Context mContext;
    private List<RightClassifyListbean.ResultBean.ClassListBean.ChildBean> mChildBeans;
    private int size;

    public ChildGoodsAdapter(Context pContext, List<RightClassifyListbean.ResultBean.ClassListBean.ChildBean> pChildBeans, int pSize) {
        mContext = pContext;
        mChildBeans = pChildBeans;
        size = pSize;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_child_class_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        RightClassifyListbean.ResultBean.ClassListBean.ChildBean childBean = mChildBeans.get(position);
        if (null != childBean) {
            holder.tv.setText(childBean.getGc_name());
            Glide.with(mContext).load(childBean.getImage()).into(holder.iv);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View pView) {
                    if (null != mCallBack) {
                        mCallBack.onItemClick(position, mChildBeans);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.goods_iv);
            tv = itemView.findViewById(R.id.goods_tv);
        }
    }

    public CallBack mCallBack;

    public void setCallBack(CallBack pCallBack) {
        mCallBack = pCallBack;
    }

    public interface CallBack {
        void onItemClick(int position, List<RightClassifyListbean.ResultBean.ClassListBean.ChildBean> list);
    }
}
