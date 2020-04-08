package com.valy.baselibrary.dialogfragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.valy.baselibrary.R
import com.valy.baselibrary.utils.OnClickLstenerInterface
import kotlinx.android.synthetic.main.dialog_talk_window.*

/**
 * @Auther: valy
 * @datetime: 2020-02-28
 * @desc:
 */
class TalkDialogFragment : DialogFragment() {
    var mlistener: OnClickLstenerInterface.OnThingClickInterface? = null
    var title_tv: TextView? = null
    var cancel_btn: TextView? = null
    var sure_btn: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val mBaseView = inflater.inflate(R.layout.dialog_talk_window, container, false)
        title_tv = mBaseView.findViewById(R.id.title_tv)
        sure_btn = mBaseView.findViewById(R.id.sure_btn)
        cancel_btn = mBaseView.findViewById(R.id.cancel_btn)
        initView()
        return mBaseView
    }

    fun newInstance(title: String?, sureStr: String?): TalkDialogFragment? {
        val fragment = TalkDialogFragment()
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("sureStr", sureStr)
        fragment.arguments = bundle
        return fragment
    }

    private fun initView() {
        if (activity != null && arguments != null && arguments!!.getString("title") != null) {
            title_tv?.text = arguments!!.getString("title")
            sure_btn?.text = arguments?.getString("sureStr")
        } else {
            dismiss()
            onDestroy()
        }

        sure_btn?.setOnClickListener {
            if (mlistener != null) {
                mlistener?.getThing(1)
                dismiss()
            }
        }
        cancel_btn?.setOnClickListener {
            dismiss()
        }
    }

    /**
     * 设置位置在底部 而且设置背景为透明
     */
    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        val params = window?.attributes
        params?.gravity = Gravity.CENTER
        params?.width = WindowManager.LayoutParams.WRAP_CONTENT
        //        params.height = 700;
        window?.attributes = params
        //设置背景透明
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}