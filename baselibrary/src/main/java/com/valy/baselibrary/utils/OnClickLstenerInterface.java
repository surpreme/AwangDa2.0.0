package com.valy.baselibrary.utils;

import android.view.View;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2019-12-21
 * @desc:
 */
public class OnClickLstenerInterface {
    public interface OnRecyClickInterface {
        void getPosition(int position);
    }

    public interface OnRecyClicksInterface {
        void getPosition(View v, int position);
    }
    public interface OnRecyClicksTInterface {
        void getT(View v, String s);
    }
    public interface OnCommitInterface<T> {
        void commit(List<T> listFirst);
    }

    public interface OnCommitInterfaces<T> {
        void commit(List<T> listFirst, View view);
    }

    //toolbar返回键
    public interface OnToolBarClickInterface {
        void back();

        void rightClick(View v);
    }

    public interface OnThingClickInterface {
        void getThing(Object msg);
    }
}
