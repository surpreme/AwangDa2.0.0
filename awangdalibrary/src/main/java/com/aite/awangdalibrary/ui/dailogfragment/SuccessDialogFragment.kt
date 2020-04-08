package com.aite.awangdalibrary.ui.dailogfragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.fragment.app.DialogFragment
import com.aite.awangdalibrary.R
import kotlinx.android.synthetic.main.dialog_state_window.*

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class SuccessDialogFragment : DialogFragment() {
    var mCountDownTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val mBaseView: View = inflater.inflate(R.layout.dialog_state_window, container, false)
        return mBaseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initDatas()
    }

    private fun initDatas() {
        if (mCountDownTimer == null)
            mCountDownTimer = object : CountDownTimer(3 * 1000, 1000) {
                override fun onFinish() {
                    dismiss()

                }

                override fun onTick(millisUntilFinished: Long) {
                }

            }
        mCountDownTimer?.start()
    }

    private fun initViews() {
        if (activity != null && arguments != null && arguments!!.getString("state") != null) {
            state_tv.text = arguments!!.getString("state")
        } else {
            dismiss()
            onDestroy()
        }
    }

    fun newInstance(state: String?): SuccessDialogFragment? {
        val fragment = SuccessDialogFragment()
        val bundle = Bundle()
        bundle.putString("state", state)
        fragment.arguments = bundle
        return fragment
    }

    /**
     * 设置位置在底部 而且设置背景为透明
     */
    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        val params = window?.attributes
        params?.gravity = Gravity.CENTER
//        params.width = WindowManager.LayoutParams.MATCH_PARENT
        //        params.height = 700;
        window?.attributes = params
        //设置背景透明
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }
}