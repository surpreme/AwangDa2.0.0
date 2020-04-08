package com.aite.awangdalibrary.ui.activity.productdetails

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.aite.awangdalibrary.R
import com.valy.baselibrary.dialog.BasePopupWindows
import com.valy.baselibrary.utils.LogUtils

/**

 * @Auther: valy

 * @datetime: 2020/3/19

 * @desc:

 */
class ShareOthersDialog(context: Context) : BasePopupWindows(context) {
    override fun initView(view: View) {
        val facebook_ll = view.findViewById<LinearLayout>(R.id.facebook_ll)
        val ins_feed_ll = view.findViewById<LinearLayout>(R.id.ins_feed_ll)
        val ins_story_ll = view.findViewById<LinearLayout>(R.id.ins_story_ll)
        if (onShareInterface != null) {
            facebook_ll.setOnClickListener {
                onShareInterface!!.onSelected("facebook")
            }
            ins_feed_ll.setOnClickListener {
                onShareInterface!!.onSelected("ins_feed")
            }
            ins_story_ll.setOnClickListener {
                onShareInterface!!.onSelected("ins_story")
            }
        }
    }

    var onShareInterface: OnShareInterface? = null

    interface OnShareInterface {
        fun onSelected(s: String)
    }

}