package com.example.green.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.homepage.HotSearchKeyListbean;
import com.example.green.config.ApiConfig;
import com.example.green.database.RecordsDao;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.HomePageModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.green.local_utils.SPUtils.KEY_USER_NAME;

public class SearchActivity extends BaseMvpActivity<CommonPresenter, HomePageModel> implements ICommonView, View.OnClickListener {

    private RecordsDao mRecordsDao;
    //默然展示词条个数
    private final int DEFAULT_RECORD_NUMBER = 10;
    private List<String> recordList = new ArrayList<>();
    private List<String> hotLsit = new ArrayList<>();
    private TagAdapter mRecordsAdapter;
    private TagAdapter mHotAdapter;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edit_query)
    EditText editText;
    @BindView(R.id.iv_clear_search)
    ImageView clearSearch;
    @BindView(R.id.iv_search)
    TextView ivSearch;
    @BindView(R.id.cl_toolbar)
    ConstraintLayout clToolbar;
    @BindView(R.id.tv_history_hint)
    TextView tvHistoryHint;
    @BindView(R.id.clear_all_records)
    ImageView clearAllRecords;
    @BindView(R.id.fl_search_records)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.fl_search_hot)
    TagFlowLayout hotTagFlowLayout;
    @BindView(R.id.iv_arrow)
    ImageView moreArrow;
    @BindView(R.id.ll_history_content)
    LinearLayout mHistoryContent;

    protected void initView() {
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        if (null != keyword) {
            editText.setText(keyword);
            editText.setSelection(keyword.length());//将光标移至文字末尾
        }
        //默认账号
        String username = SPUtils.getInstance().getValue(KEY_USER_NAME, "");
        //初始化数据库
        mRecordsDao = new RecordsDao(this, username);
        //创建历史标签适配器
        //为标签设置对应的内容
        mRecordsAdapter = new TagAdapter<String>(recordList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv_history,
                        tagFlowLayout, false);
                //为标签设置对应的内容
                tv.setText(s);
                return tv;
            }
        };


        tagFlowLayout.setAdapter(mRecordsAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                //清空editText之前的数据
                editText.setText("");
                //将获取到的字符串传到搜索结果界面,点击后搜索对应条目内容
                editText.setText(recordList.get(position));
                editText.setSelection(editText.length());
            }
        });
        //删除某个条目
        tagFlowLayout.setOnLongClickListener(new TagFlowLayout.OnLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                showDialog("确定要删除该条历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除某一条记录
                        mRecordsDao.deleteRecord(recordList.get(position));
                    }
                });
            }
        });

        //view加载完成时回调
        tagFlowLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean isOverFlow = tagFlowLayout.isOverFlow();
                boolean isLimit = tagFlowLayout.isLimit();
                if (isLimit && isOverFlow) {
                    moreArrow.setVisibility(View.VISIBLE);
                } else {
                    moreArrow.setVisibility(View.GONE);
                }
            }
        });
        // 返回
        ivBack.setOnClickListener(this);
        // 更多记录
        moreArrow.setOnClickListener(this);
        //清除所有记录
        clearAllRecords.setOnClickListener(this);
        // 搜索
        ivSearch.setOnClickListener(this);
        // 清除搜索历史
        clearSearch.setOnClickListener(this);

        mRecordsDao.setNotifyDataChanged(new RecordsDao.NotifyDataChanged() {
            @Override
            public void notifyDataChanged() {
                initData();
            }
        });

    }

    private void showDialog(String dialogTitle, @NonNull DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
        builder.setMessage(dialogTitle);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    protected void initData() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(mRecordsDao.getRecordsByNumber(DEFAULT_RECORD_NUMBER));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        recordList.clear();
                        recordList = s;
                        if (null == recordList || recordList.size() == 0) {
                            mHistoryContent.setVisibility(View.GONE);
                        } else {
                            mHistoryContent.setVisibility(View.VISIBLE);
                        }
                        if (mRecordsAdapter != null) {
                            mRecordsAdapter.setData(recordList);
                            mRecordsAdapter.notifyDataChanged();
                        }
                    }
                });

        // 搜索页面热门搜索词
        mPresenter.getData(ApiConfig.HOT_SEARCH_KEY);
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
        return R.layout.activity_search;
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HOT_SEARCH_KEY:
                HotSearchKeyListbean hotSearchKeyListbean = (HotSearchKeyListbean) t[0];
                if (null != hotSearchKeyListbean && hotSearchKeyListbean.getCode().equals("200")) {
                    hotLsit.clear();
                    hotLsit.addAll(hotSearchKeyListbean.getResult().getList());

                    mHotAdapter = new TagAdapter<String>(hotLsit) {
                        @Override
                        public View getView(FlowLayout parent, int position, String pS) {
                            TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv_hot,
                                    hotTagFlowLayout, false);
                            //为标签设置对应的内容
                            tv.setText(pS);
                            return tv;
                        }
                    };
                    hotTagFlowLayout.setAdapter(mHotAdapter);
                    hotTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public void onTagClick(View view, int position, FlowLayout parent) {
                            //清空editText之前的数据
                            editText.setText("");
                            //将获取到的字符串传到搜索结果界面,点击后搜索对应条目内容
                            editText.setText(hotLsit.get(position));
                            editText.setSelection(editText.length());
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onClick(View pView) {
        switch (pView.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_arrow:
                tagFlowLayout.setLimit(false);
                mRecordsAdapter.notifyDataChanged();
                break;
            case R.id.clear_all_records:
                showDialog("确定要删除全部历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagFlowLayout.setLimit(true);
                        //清除所有数据
                        mRecordsDao.deleteUsernameAllRecords();
                    }
                });
                break;
            case R.id.iv_search:
                String record = editText.getText().toString();
                if (!TextUtils.isEmpty(record)) {
                    //添加数据
                    mRecordsDao.addRecords(record);
                    Intent intent = new Intent(SearchActivity.this, SearchListActivity.class);
                    intent.putExtra("keyword", editText.getText().toString().trim());
                    startActivity(intent);

                }
                break;
            case R.id.iv_clear_search:
                editText.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mRecordsDao.closeDatabase();
        mRecordsDao.removeNotifyDataChanged();
        super.onDestroy();
    }
}

