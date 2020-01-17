package com.example.green.adapter.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.green.R;
import com.example.green.bean.store.StoreListbean;

import java.util.List;

public class MyStoreShowListAdapter extends RecyclerView.Adapter<MyStoreShowListAdapter.ViewHolder> {
    private Context mContext;
    private List<StoreListbean.ResultBean.SearchListGoodsBean> mList;

    public MyStoreShowListAdapter(Context pContext, List<StoreListbean.ResultBean.SearchListGoodsBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_store_show_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StoreListbean.ResultBean.SearchListGoodsBean searchListGoodsBean = mList.get(position);
        if (null != searchListGoodsBean) {
            Glide.with(mContext).load(searchListGoodsBean.getGoods_image()).into(holder.goods_iv);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View pView) {
                    if (null != mCallback) {
                        mCallback.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView goods_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            goods_iv = itemView.findViewById(R.id.goods_iv);
        }
    }

    private Callback mCallback;

    public void setCallback(Callback pCallback) {
        mCallback = pCallback;
    }

    public interface Callback {
        void onItemClick(int position);
    }
}
