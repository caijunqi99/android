package com.example.green.adapter.classify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.bean.classify.RightClassifyListbean;
import com.example.green.ui.activity.SearchListActivity;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.List;

public class MyRightClassifyAdapter extends RecyclerView.Adapter {

    private static final int TYPE_GOODS = 1;
    private static final String TAG = "MyRightClassifyAdapter";
    private List<RightClassifyListbean.ResultBean.ClassListBean> mClassListBeans;
    private Context mContext;

    public MyRightClassifyAdapter(List<RightClassifyListbean.ResultBean.ClassListBean> pClassListBeans, Context pContext) {
        mClassListBeans = pClassListBeans;
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_GOODS) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_right_child, null);
            GoodsViewHolder goodsViewHolder = new GoodsViewHolder(inflate);
            return goodsViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RightClassifyListbean.ResultBean.ClassListBean classListBean = mClassListBeans.get(position);
        if (null != classListBean) {
            if (holder instanceof GoodsViewHolder) {
                GoodsViewHolder goodsViewHolder = (GoodsViewHolder) holder;
                goodsViewHolder.mTextView.setText(classListBean.getGc_name());

                List<RightClassifyListbean.ResultBean.ClassListBean.ChildBean> child = classListBean.getChild();
                if (child.size() > 0) {
                    goodsViewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));

                    ChildGoodsAdapter childGoodsAdapter = new ChildGoodsAdapter(mContext, child, child.size());
                    goodsViewHolder.mRecyclerView.setAdapter(childGoodsAdapter);
                    childGoodsAdapter.notifyDataSetChanged();

                    childGoodsAdapter.setCallBack(new ChildGoodsAdapter.CallBack() {
                        @Override
                        public void onItemClick(int position, List<RightClassifyListbean.ResultBean.ClassListBean.ChildBean> list) {
                            Intent intent = new Intent(mContext, SearchListActivity.class);

                            Log.e(TAG, "onItemClick: " + list.get(position).getGc_id());
                            intent.putExtra("gcId", list.get(position).getGc_id());
                            mContext.startActivity(intent);
                        }
                    });
                }
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mClassListBeans != null) {
            return TYPE_GOODS;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mClassListBeans.size();
    }

    private class GoodsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private RecyclerView mRecyclerView;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.class_name);
            mRecyclerView = itemView.findViewById(R.id.child_class_recyclerview);
        }
    }
}
