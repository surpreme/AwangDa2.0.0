package com.community.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aite.a.activity.WebActivity;
import com.aite.a.base.BaseActivity;
import com.aite.a.parse.NetRun;
import com.aite.a.view.PullToRefreshLayout;
import com.aite.a.view.PullableListView;
import com.jiananshop.a.R;
import com.bumptech.glide.Glide;
import com.community.adapter.Article4Adapter;
import com.community.adapter.ForumListMenuAdapter;
import com.community.model.ForumClassifyInfo;
import com.community.model.ForumListInfo;

import java.util.List;

/**
 * 帖子列表
 * Created by mayn on 2018/9/19.
 */
public class ForumListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_goback, iv_search;
    private RecyclerView rv_menu;
    private PullToRefreshLayout refresh_view;
    private PullableListView lv_list;
    private LinearLayout ll_null;
    private View header;

    private RelativeLayout rl_item;
    private TextView tv_title;
    private ImageView iv_postimg1, iv_postimg2, iv_postimg3;
    private TextView tv_zd, tv_titme;

    private List<ForumClassifyInfo> forumClassifyInfo;
    private ForumListMenuAdapter forumListMenuAdapter;
    private MyListener myListenr;
    private int refreshtype = 0, curpage = 1;
    private String keyword = "", pagesize = "20", orderType = "1";
    private ForumListInfo forumListInfo;
    private Article4Adapter article4Adapter;
    private NetRun netRun;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case forum_list_id://帖子列表
                    if (msg.obj != null) {
                        forumListInfo = (ForumListInfo) msg.obj;
                        if (forumListInfo.list == null || forumListInfo.list.size() == 0) {
                            ll_null.setVisibility(View.VISIBLE);
                        } else {
                            ll_null.setVisibility(View.GONE);
                            if (refreshtype == 0 || article4Adapter == null) {
                                tv_title.setText(forumListInfo.list.get(0).article_title);
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 0) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(0)).into(iv_postimg1);
                                }
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 1) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(1)).into(iv_postimg2);
                                }
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 2) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(2)).into(iv_postimg3);
                                }
                                tv_titme.setText(forumListInfo.list.get(0).article_publish_time + "\t" + forumListInfo.list.get(0).article_publisher_name);
                                lv_list.addHeaderView(header);
                                article4Adapter = new Article4Adapter(ForumListActivity.this, forumListInfo.list);
                                lv_list.setAdapter(article4Adapter);
                            } else if (refreshtype == 1) {
                                tv_title.setText(forumListInfo.list.get(0).article_title);
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 0) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(0)).into(iv_postimg1);
                                }
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 1) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(1)).into(iv_postimg2);
                                }
                                if (forumListInfo.list.get(0).article_image_all != null && forumListInfo.list.get(0).article_image_all.size() > 2) {
                                    Glide.with(ForumListActivity.this).load(forumListInfo.list.get(0).article_image_all.get(2)).into(iv_postimg3);
                                }
                                tv_titme.setText(forumListInfo.list.get(0).article_publish_time + "\t" + forumListInfo.list.get(0).article_publisher_name);
                                article4Adapter.refreshData(forumListInfo.list);
                                myListenr.refreshSucceed();
                            } else if (refreshtype == 2) {
                                article4Adapter.addData(forumListInfo.list);
                                myListenr.loadMoreSucceed();
                            }
                        }
                    }
                    break;
                case forum_list_err:
                    Toast.makeText(appSingleton, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case forum_classify_id://帖子分类
                    if (msg.obj != null) {
                        forumClassifyInfo = (List<ForumClassifyInfo>) msg.obj;
                        if (forumClassifyInfo != null && forumClassifyInfo.size() != 0) {
                            forumClassifyInfo.get(0).ispick = true;
                            orderType = forumClassifyInfo.get(0).class_id;
                            forumListMenuAdapter = new ForumListMenuAdapter(appSingleton, forumClassifyInfo, handler);
                            rv_menu.setAdapter(forumListMenuAdapter);
                            netRun.ForumList(keyword, pagesize, curpage + "", orderType);
                        }
                    }
                    break;
                case forum_classify_err:
                    Toast.makeText(appSingleton, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case FORUMMENU://选中分类
                    if (msg.obj != null) {
                        String str = (String) msg.obj;
                        orderType = str;
                        netRun.ForumList(keyword, pagesize, curpage + "", orderType);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumlist);
        findViewById();
    }

    @Override
    protected void findViewById() {
        iv_goback = (ImageView) findViewById(R.id.iv_goback);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        rv_menu = (RecyclerView) findViewById(R.id.rv_menu);
        refresh_view = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        lv_list = (PullableListView) findViewById(R.id.lv_list);
        ll_null = (LinearLayout) findViewById(R.id.ll_null);

        header = View.inflate(this, R.layout.item_forumheader, null);
        rl_item = (RelativeLayout) header.findViewById(R.id.rl_item);
        tv_title = (TextView) header.findViewById(R.id.tv_title);
        iv_postimg1 = (ImageView) header.findViewById(R.id.iv_postimg1);
        iv_postimg2 = (ImageView) header.findViewById(R.id.iv_postimg2);
        iv_postimg3 = (ImageView) header.findViewById(R.id.iv_postimg3);
        tv_zd = (TextView) header.findViewById(R.id.tv_zd);
        tv_titme = (TextView) header.findViewById(R.id.tv_titme);
        initView();
    }

    @Override
    protected void initView() {
        keyword = getIntent().getStringExtra("keyword");
        if (keyword == null) {
            keyword = "";
        }
        iv_goback.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        rl_item.setOnClickListener(this);
        myListenr = new MyListener();
        refresh_view.setOnRefreshListener(myListenr);
        netRun = new NetRun(this, handler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_menu.setLayoutManager(linearLayoutManager);//菜单
        initData();
    }

    @Override
    protected void initData() {
        netRun.ForumClassify();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
                break;
            case R.id.iv_search://搜索
                Intent intent2 = new Intent(ForumListActivity.this, SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_item://第一条
                Intent intent = new Intent(ForumListActivity.this, WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", forumListInfo.list.get(0).url);
                bundle.putString("title", forumListInfo.list.get(0).article_title);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    public class MyListener implements PullToRefreshLayout.OnRefreshListener {

        private PullToRefreshLayout pullToRefreshLayout;

        private android.os.Handler refreshHandler = new android.os.Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case pull_down_refresh_success:// 下拉刷新成功
                        if (pullToRefreshLayout != null) {
                            pullToRefreshLayout
                                    .refreshFinish(PullToRefreshLayout.SUCCEED);
                        }
                        break;
                    case pull_up_loaded_success:// 上拉加载成功
                        if (pullToRefreshLayout != null) {
                            pullToRefreshLayout
                                    .loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }
                        break;
                }
            }

            ;
        };

        /**
         * 下拉式刷新成功
         */
        public void refreshSucceed() {
            refreshHandler.sendEmptyMessageDelayed(pull_down_refresh_success,
                    400);
        }

        /**
         * 上拉加载成功
         */
        public void loadMoreSucceed() {
            refreshHandler.sendEmptyMessageDelayed(pull_up_loaded_success, 400);
        }

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            this.pullToRefreshLayout = pullToRefreshLayout;
            refreshtype = 1;
            curpage = 1;
            Log.i("-----------------", "刷新  ");
            netRun.ForumList(keyword, pagesize, curpage + "", orderType);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            this.pullToRefreshLayout = pullToRefreshLayout;
            if (forumListInfo != null && forumListInfo.is_nextpage.equals("1")) {
                refreshtype = 2;
                curpage++;
                netRun.ForumList(keyword, pagesize, curpage + "", orderType);
            } else {
                Toast.makeText(ForumListActivity.this, getString(R.string.find_reminder3), Toast.LENGTH_SHORT).show();
                loadMoreSucceed();
            }

        }
    }

    @Override
    public void ReGetData() {

    }
}
