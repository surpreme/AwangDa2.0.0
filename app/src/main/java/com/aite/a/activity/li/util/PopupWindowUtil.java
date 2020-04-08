package com.aite.a.activity.li.util;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.aite.a.activity.li.activity.login.AreaCodeAdapter;
import com.aite.a.activity.li.adapter.PopAdapter;
import com.jiananshop.a.R;

import java.util.List;

public class PopupWindowUtil {
    private static PopupWindowUtil mInstance;
    private static int nearbottomlayoutid = R.layout.near_pop_layout;
    private int recyLayoutid = R.layout.pop_list_layout;
    private static final int popbindingacountchoicenewslayoutid = R.layout.pop_binder_newsuser;
    private static final int popbindingacounteditmessagelayoutid = R.layout.pop_edit_acountpassword;
    private static final int popsharegoodslayoutid = R.layout.pop_share_goods;
    private static final int bottom_recyandcanceLayout = R.layout.base_bottom_recyandcancel_pop;
    private static final int popsuredailoglayoutid = R.layout.dialog_sure_talk_window;
    private static final int dailog_talk_Layout = R.layout.base_talk_dailog_pop;

    private PopupWindow popupWindow;

    public static PopupWindowUtil getmInstance() {
        if (mInstance == null) {
            synchronized (PopupWindowUtil.class) {
                if (mInstance == null) {
                    mInstance = new PopupWindowUtil();
                }
            }
        }
        return mInstance;
    }

    public void setNearbottomPop(final Context context, View view, final List<String> list, PopAdapter.OnclickInterface onclickInterface) {
        @SuppressLint("InflateParams") View more_view = LayoutInflater.from(context).inflate(nearbottomlayoutid, null);
        setBackGroundAlpha(1.0f, context);
        popupWindow = new PopupWindow(more_view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(more_view);
        popupWindow.showAsDropDown(view);
        RecyclerView recyclerView = more_view.findViewById(R.id.pop_recy);

//        final List<String> list = new ArrayList<>();
//        list.add("附近");
//        list.add("500m以内");
//        list.add("1km-2km");
//        list.add("2km-5km");
//        list.add("5km以上");

        PopAdapter popAdapter = new PopAdapter(context, list);
        recyclerView.setAdapter(popAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        popAdapter.setOnclickInterface(onclickInterface);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();

            }
        });
    }

    public void showDialogPopupWindow(final Context context, String titles, String information, String sureStr, String cancelStr, View.OnClickListener listenersure, View.OnClickListener listenercancel) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(dailog_talk_Layout, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final TextView title_tv = view.findViewById(R.id.title_tv);
        final TextView information_tv = view.findViewById(R.id.information_tv);
        final Button sure_btn = view.findViewById(R.id.sure_btn);
        final Button cancel_btn = view.findViewById(R.id.cancel_btn);
        information_tv.setLines(3);
        view.setAlpha(0.98f);
        if (isStringUnEmpty(titles)) title_tv.setText(titles);
        if (isStringUnEmpty(sureStr)) sure_btn.setText(sureStr);
        if (isStringUnEmpty(cancelStr)) cancel_btn.setText(cancelStr);
        information_tv.setText(information);
        sure_btn.setOnClickListener(listenersure);
        if (listenercancel == null) {
            cancel_btn.setOnClickListener(v -> {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            });
        } else {
            cancel_btn.setOnClickListener(listenercancel);
        }

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showSureDialogPopupWindow(final Context context, String titles, String information, String sureStr, View.OnClickListener listenersure) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(popsuredailoglayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
//        popupWindow = new PopupWindow(view, 200, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final TextView title_tv = view.findViewById(R.id.title_tv);
        final TextView information_tv = view.findViewById(R.id.information_tv);
        final Button sure_btn = view.findViewById(R.id.sure_btn);
        information_tv.setLines(3);
        view.setAlpha(0.9f);
        if (isStringUnEmpty(titles)) title_tv.setText(titles);
        if (isStringUnEmpty(sureStr)) sure_btn.setText(sureStr);
        information_tv.setText(information);
        sure_btn.setOnClickListener(listenersure);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            setBackGroundAlpha(1.0f, context);
            dismissPopWindow();
        });

    }

    private boolean isStringUnEmpty(String str) {
        return str != null && !str.equals("") && !str.equals("null");
    }

    public void showBottomRecyAndCancelPopupWindow(final Context context, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager, int cancelTextColor) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(bottom_recyandcanceLayout, null);
        setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        TextView cancel_tv = view.findViewById(R.id.cancel_tv);
        if (cancelTextColor != 0)
            cancel_tv.setTextColor(cancelTextColor);
        cancel_tv.setOnClickListener(v -> popupWindow.dismiss());
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(linearLayoutManager);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(() -> setBackGroundAlpha(1.0f, context));

    }

    public interface OnBindingUserChoiceBackInterface {
        void onGetWay(String way);
    }

    public interface OnBindingEditMsgBackInterface {
        void onGetAcount(String acount, String password);
    }

    public void showBindingEditMsgPop(Context context, OnBindingEditMsgBackInterface onBindingEditMsgBackInterface) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popbindingacounteditmessagelayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, 800, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        EditText account_edit = view.findViewById(R.id.account_edit);
        EditText password_edit = view.findViewById(R.id.password_edit);
        Button sumbit_btn = view.findViewById(R.id.sumbit_btn);

        sumbit_btn.setOnClickListener(v -> {
            if (account_edit.getText().toString().equals("") || password_edit.getText().toString().equals("")) {
                Toast.makeText(context, "请检查账号信息", Toast.LENGTH_SHORT).show();
                return;
            }
            onBindingEditMsgBackInterface.onGetAcount(account_edit.getText().toString(), password_edit.getText().toString());
            dismissPopWindow();

        });
        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });
    }

    public void showbindingPopupWindow(final Context context, OnBindingUserChoiceBackInterface onBindingUserChoiceBackInterface) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popbindingacountchoicenewslayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        TextView binderOlderTv = view.findViewById(R.id.binder_older_tv);
        TextView newUserTv = view.findViewById(R.id.new_user_tv);
        TextView cancelTv = view.findViewById(R.id.cancel_tv);
        binderOlderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
                onBindingUserChoiceBackInterface.onGetWay("BINDEROLDER");
            }
        });
        newUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
                onBindingUserChoiceBackInterface.onGetWay("NEWSUSERS");

            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();

            }
        });
        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showShareGoodsPopupWindow(final Context context, OnBindingUserChoiceBackInterface onBindingUserChoiceBackInterface) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popsharegoodslayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        LinearLayout facebook_ll = view.findViewById(R.id.facebook_ll);
        LinearLayout ins_story_ll = view.findViewById(R.id.ins_story_ll);
        LinearLayout ins_feed_ll = view.findViewById(R.id.ins_feed_ll);
        facebook_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
                if (onBindingUserChoiceBackInterface != null)
                    onBindingUserChoiceBackInterface.onGetWay("facebook");
            }
        });
        ins_story_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
                if (onBindingUserChoiceBackInterface != null)
                    onBindingUserChoiceBackInterface.onGetWay("ins_story");

            }
        });
        ins_feed_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
                if (onBindingUserChoiceBackInterface != null)
                    onBindingUserChoiceBackInterface.onGetWay("ins_feed");

            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showChoiceAreaPopupwindow(final Context context, View ui, AreaCodeAdapter areaCodeAdapter) {
        @SuppressLint("InflateParams") View more_icon_view = LayoutInflater.from(context).inflate(recyLayoutid, null);
        popupWindow = new PopupWindow(more_icon_view, 270, 450, true);
        popupWindow.setContentView(more_icon_view);
        RecyclerView recyclerView = more_icon_view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(areaCodeAdapter);
        popupWindow.showAsDropDown(ui, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismissPopWindow();


            }
        });

    }

    public void dismissPopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
    }

    void setBackGroundAlpha(float alpha, Context context) {
        WindowManager.LayoutParams layoutParams = ((AppCompatActivity) context).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((AppCompatActivity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((AppCompatActivity) context).getWindow().setAttributes(layoutParams);
    }
}
