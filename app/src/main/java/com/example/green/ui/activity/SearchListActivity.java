package com.example.green.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.green.R;
import com.example.green.adapter.store.MyFragmentAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.model.HomePageModel;
import com.example.green.ui.fragment.search.SearchGoodsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchListActivity extends BaseMvpActivity<CommonPresenter, HomePageModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.keyword)
    TextView mKeyword;
    @BindView(R.id.rl_search_view)
    RelativeLayout mSearch;
    @BindView(R.id.tab)
    XTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private String keyWord; // 关键词
    private String gcId; // 商品gc_id
    private MyFragmentAdapter mMyFragmentAdapter;
    private List<Fragment> mFragments;
    private static final String TAG = "SearchListActivity";

    @Override
    protected void initView() {
//        mKeyword.setFocusable(false);
//        mKeyword.setFocusableInTouchMode(false);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        keyWord = intent.getStringExtra("keyword");
        gcId = intent.getStringExtra("gcId");
        mKeyword.setText(keyWord);

        mFragments = new ArrayList<>();
        SearchGoodsFragment searchGoodsFragment_synthesize = SearchGoodsFragment.newInstance(keyWord, gcId, 0);
        SearchGoodsFragment searchGoodsFragment_sales = SearchGoodsFragment.newInstance(keyWord, gcId, 3);
        SearchGoodsFragment searchGoodsFragment_price = SearchGoodsFragment.newInstance(keyWord, gcId, 2);

        mFragments.add(searchGoodsFragment_synthesize);
        mFragments.add(searchGoodsFragment_sales);
        mFragments.add(searchGoodsFragment_price);

        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragments);
        mTab.addTab(mTab.newTab().setText("综合"));
        mTab.addTab(mTab.newTab().setText("销量"));
        mTab.addTab(mTab.newTab().setText("价格"));
        mVp.setAdapter(mMyFragmentAdapter);

        mVp.setCurrentItem(0);
        mTab.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new XTabLayout.TabLayoutOnPageChangeListener(mTab));
//        mKeyword.addTextChangedListener(keyEditInput); // 监听验证码输入状态

    }

   /* private TextWatcher keyEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            final String key = mKeyword.getText().toString().trim();
            mKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        toastActivity(key);
                        //点击搜索的时候隐藏软键盘
                        hideKeyboard(mKeyword);
                        // 在这里写搜索的操作,一般都是网络请求数据
                        mFragments.clear();
                        SearchGoodsFragment searchGoodsFragment_synthesize = SearchGoodsFragment.newInstance(key, "", 0);
                        SearchGoodsFragment searchGoodsFragment_sales = SearchGoodsFragment.newInstance(key, "", 3);
                        SearchGoodsFragment searchGoodsFragment_price = SearchGoodsFragment.newInstance(key, "", 2);
                        mFragments.add(searchGoodsFragment_synthesize);
                        mFragments.add(searchGoodsFragment_sales);
                        mFragments.add(searchGoodsFragment_price);
                        mMyFragmentAdapter.notifyDataSetChanged();

                        LocalBroadcastManager.getInstance(SearchListActivity.this)
                                .sendBroadcast(new Intent(BaseActivity.SEARCH_SUCCESS));
                        return true;
                    }

                    return false;
                }
            });
        }
    };*/

    @Override
    protected void initData() {
    }

    @Override
    protected HomePageModel initModel() {
        return new HomePageModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_list;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
    }

    @OnClick({R.id.back, R.id.rl_search_view})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_search_view:
                Intent intent = new Intent(SearchListActivity.this, SearchActivity.class);
                intent.putExtra("keyword", keyWord);
                startActivity(intent);
                break;
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view :一般为EditText
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
