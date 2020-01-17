package com.example.green.ui.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.adapter.shoptrolley.ShoppingCarAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.Logoutbean;
import com.example.green.bean.shopping.ChangeGoodsNumInfobean;
import com.example.green.bean.shopping.ShoppingtrolleyListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.ShopModel;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.example.green.ui.activity.shopping.AffirmOrderActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends BaseMvpFragment<CommonPresenter, ShopModel>
        implements ICommonView {

    static ShopFragment fragment;
    @BindView(R.id.goods_num)
    TextView mGoodsNum;
    @BindView(R.id.editor)
    TextView mEditor;
    @BindView(R.id.rl_editor)
    RelativeLayout mRlEditor;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.elv_shopping_car)
    ExpandableListView mElvShoppingCar;
    @BindView(R.id.info)
    TextView mInfo;
    @BindView(R.id.iv_select_all)
    ImageView mIvSelectAll;
    @BindView(R.id.ll_select_all)
    LinearLayout mLlSelectAll;
    @BindView(R.id.bt_settlement)
    Button mBtSettlement;
    @BindView(R.id.bt_delete)
    Button mBtDelete;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.rl_total_price)
    RelativeLayout mRlTotalPrice;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.iv_no_contant)
    ImageView mIvNoContant;
    @BindView(R.id.rl_no_contant)
    RelativeLayout mRlNoContant;
    private String key;
    ShoppingCarAdapter shoppingCarAdapter;
    private List<ShoppingtrolleyListbean.ResultBean.CartListBean> datas;
    private static final String TAG = "ShopFragment";
    private String mCartId;
    private List<String> cartList; // 立即结算
    private String CartId = "";

    public static ShopFragment newInstance() {
        if (fragment == null) fragment = new ShopFragment();
        return fragment;
    }

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected ShopModel initModel() {
        return new ShopModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        datas = new ArrayList<>();
        cartList = new ArrayList<>();
        shoppingCarAdapter = new ShoppingCarAdapter(getContext(), mLlSelectAll, mIvSelectAll, mBtSettlement, mBtDelete, mRlTotalPrice, mTvTotalPrice);
        mElvShoppingCar.setAdapter(shoppingCarAdapter);

        initExpandableListView();
        // 购物车 立即结算回调
        shoppingCarAdapter.setOnSelectedGoodsListener(new ShoppingCarAdapter.OnSelectedGoodsListener() {
            @Override
            public void onSelectedGoods(List<ShoppingtrolleyListbean.ResultBean.CartListBean> pCartListBeans) {
                for (int i = 0; i < pCartListBeans.size(); i++) {
                    List<ShoppingtrolleyListbean.ResultBean.CartListBean.GoodsBean> goods = pCartListBeans.get(i).getGoods();
                    for (int j = 0; j < goods.size(); j++) {
                        int goods_num = goods.get(j).getGoods_num();
                        int cartId = goods.get(j).getCart_id();
                        String cart_id = cartId + "|" + goods_num;
                        cartList.add(cart_id);
                    }
                }
                for (int i = 0; i < cartList.size(); i++) {
                    CartId += cartList.get(i) + ",";
                }
                String substring = CartId.substring(0, CartId.length() - 1);
                Intent intent = new Intent(getContext(), AffirmOrderActivity.class);
                intent.putExtra("cart_id", substring);
                intent.putExtra("ifCart", "1");
                startActivity(intent);
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getData(ApiConfig.SHOPPING_CART, key, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setEnableLoadmore(false);
        // 子条目长按删除
        mElvShoppingCar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> pAdapterView, View pView, int position, long id) {
                final long packedPosition = mElvShoppingCar.getExpandableListPosition(position);
                final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
                //长按的是group的时候，childPosition = -1，这是子条目的长按点击
                mCartId = datas.get(groupPosition).getGoods().get(childPosition).getCart_id() + "";
                showHintDialog();
                return true;
            }
        });
        // 子条目点击进入详情
        mElvShoppingCar.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView pExpandableListView, View pView, int groupPosition, int childPosition, long pL) {
                int goods_id = datas.get(groupPosition).getGoods().get(childPosition).getGoods_id();
                Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                intent.putExtra("goodsId", goods_id + "");
                startActivity(intent);
                return false;
            }
        });
    }

    private void showHintDialog() {
        View view = View.inflate(getContext(), R.layout.dialog_withdraw_hint, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(getContext(), 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
//        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        tv_message.setText("确定删除此商品");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                mPresenter.getData(ApiConfig.DELETE_CART, key, mCartId);
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
            }
        });
    }

    private void initExpandableListView() {

        //删除的回调
        shoppingCarAdapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {
            @Override
            public void onDelete() {
//                initDelete();
                /**
                 * 实际开发中，在此请求删除接口，删除成功后，
                 * 通过initExpandableListViewData（）方法刷新购物车数据。
                 * 注：通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
                 *                  GoodsBean的isSelect属性，判断商品是否被选中，
                 *                  （true为选中，false为未选中）
                 */
            }
        });

        //修改商品数量的回调
        shoppingCarAdapter.setOnChangeCountListener(new ShoppingCarAdapter.OnChangeCountListener() {
            @Override
            public void onChangeCount(String cart_id, int good_num) {
                /**
                 * 实际开发中，在此请求修改商品数量的接口，商品数量修改成功后，
                 * 通过initExpandableListViewData（）方法刷新购物车数据。
                 */
                mPresenter.getData(ApiConfig.CHANGE_GOODS_NUM, key, good_num + "", cart_id);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.SHOPPING_CART, key, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SHOPPING_CART:
                ShoppingtrolleyListbean shoppingtrolleyListbean = (ShoppingtrolleyListbean) t[0];
                if (null != shoppingtrolleyListbean && shoppingtrolleyListbean.getCode().equals("200")) {
                    List<ShoppingtrolleyListbean.ResultBean.CartListBean> cart_list = shoppingtrolleyListbean.getResult().getCart_list();

                    int load = (int) t[1];
                    if (load == LoadConfig.NORMAL) {
                        datas.addAll(cart_list);
                    } else if (load == LoadConfig.REFRESH) {
                        datas.clear();
                        mTvTotalPrice.setText("¥0.00");
                        mIvSelectAll.setBackgroundResource(R.mipmap.unselect);
                        datas.addAll(cart_list);
                        mSmartRefreshLayout.finishRefresh();
                    }
                    initExpandableListViewData(datas);
                }
                break;
            case ApiConfig.DELETE_CART:
                Logoutbean logoutbean = (Logoutbean) t[0];
                if (null != logoutbean && logoutbean.getCode().equals("200")) {
                    mPresenter.getData(ApiConfig.SHOPPING_CART, key, LoadConfig.REFRESH);
                    ToastUtils.show(getContext(), "商品删除成功");
                } else {
                    ToastUtils.show(getContext(), logoutbean.getMessage());
                }
                break;
            case ApiConfig.CHANGE_GOODS_NUM:
                ChangeGoodsNumInfobean changeGoodsNumInfobean = (ChangeGoodsNumInfobean) t[0];
                if (null != changeGoodsNumInfobean && changeGoodsNumInfobean.getCode().equals("200")) {
                    mPresenter.getData(ApiConfig.SHOPPING_CART, key, LoadConfig.REFRESH);
                }
                break;
        }
    }

    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
    private void initExpandableListViewData(List<ShoppingtrolleyListbean.ResultBean.CartListBean> datas) {
//        if (datas != null && datas.size() > 0) {
        //刷新数据时，保持当前位置
        shoppingCarAdapter.setData(datas);
        //使所有组展开
        for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
            mElvShoppingCar.expandGroup(i);
        }

        //使组点击无效果
        mElvShoppingCar.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });

            /*mEditor.setVisibility(View.VISIBLE);
            mEditor.setText("编辑");
            mRlNoContant.setVisibility(View.GONE);
            mElvShoppingCar.setVisibility(View.VISIBLE);
            mRlBottom.setVisibility(View.VISIBLE);
            mRlTotalPrice.setVisibility(View.VISIBLE);
            mBtSettlement.setVisibility(View.VISIBLE);
            mBtDelete.setVisibility(View.GONE);*/
       /* } else {
            mEditor.setVisibility(View.GONE);
            mRlNoContant.setVisibility(View.VISIBLE);
            mElvShoppingCar.setVisibility(View.GONE);
            mRlBottom.setVisibility(View.GONE);
        }*/
    }

    /**
     * 判断是否要弹出删除的dialog
     * 通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
     * GoodsBean的isSelect属性，判断商品是否被选中，
     */
    private void initDelete() {
        //判断是否有店铺或商品被选中
        //true为有，则需要刷新数据；反之，则不需要；
        boolean hasSelect = false;
        //创建临时的List，用于存储没有被选中的购物车数据
        List<ShoppingtrolleyListbean.ResultBean.CartListBean> datasTemp = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {

            List<ShoppingtrolleyListbean.ResultBean.CartListBean.GoodsBean> goods = datas.get(i).getGoods();
            boolean isSelect_shop = datas.get(i).getIsSelect_shop();

            if (isSelect_shop) {
                hasSelect = true;
                //跳出本次循环，继续下次循环。
                continue;
            } else {
                datasTemp.add(datas.get(i).clone());
                datasTemp.get(datasTemp.size() - 1).setGoods(new ArrayList<ShoppingtrolleyListbean.ResultBean.CartListBean.GoodsBean>());
            }

            for (int y = 0; y < goods.size(); y++) {
                ShoppingtrolleyListbean.ResultBean.CartListBean.GoodsBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.getIsSelect();

                if (isSelect) {
                    hasSelect = true;
                } else {
                    datasTemp.get(datasTemp.size() - 1).getGoods().add(goodsBean);
                }
            }
        }

        if (hasSelect) {
            showDeleteDialog(datasTemp);
        } else {
            ToastUtils.show(getContext(), "请选择要删除的商品");
        }
    }

    /**
     * 展示删除的dialog（可以自定义弹窗，不用删除即可）
     *
     * @param datasTemp
     */
    private void showDeleteDialog(final List<ShoppingtrolleyListbean.ResultBean.CartListBean> datasTemp) {


        View view = View.inflate(getContext(), R.layout.dialog_two_btn, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(getContext(), 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        tv_message.setText("确定要删除商品吗？");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                datas = datasTemp;
//                mPresenter.getData(ApiConfig.DELETE_CART, key, mCartId);
                initExpandableListViewData(datas);
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
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

    @OnClick(R.id.editor)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.editor:
                String edit = mEditor.getText().toString().trim();
                if (edit.equals("编辑")) {
                    mEditor.setText("完成");
                    mRlTotalPrice.setVisibility(View.GONE);
                    mBtSettlement.setVisibility(View.GONE);
                    mBtDelete.setVisibility(View.VISIBLE);
                } else {
                    mEditor.setText("编辑");
                    mRlTotalPrice.setVisibility(View.VISIBLE);
                    mBtSettlement.setVisibility(View.VISIBLE);
                    mBtDelete.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);

        switch (intent.getAction()) {
            case SUBMIT_ORDER:// 提交订单成功
                mPresenter.getData(ApiConfig.SHOPPING_CART, key, LoadConfig.REFRESH);
                break;
        }
    }
}
