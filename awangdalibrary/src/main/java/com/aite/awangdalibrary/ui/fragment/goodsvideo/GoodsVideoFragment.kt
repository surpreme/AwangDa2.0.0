package com.aite.awangdalibrary.ui.fragment.goodsvideo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.GoodsVideoActivity
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_goods_video.*


/**

 * @Auther: valy

 * @datetime: 2020/4/7

 * @desc:

 */
class GoodsVideoFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_goods_video
    fun bulider(imageUri: String, videoUri: String): GoodsVideoFragment {
        val fragment = GoodsVideoFragment()
        val bundle = Bundle()
        bundle.putString("imageUri", imageUri)
        bundle.putString("videoUri", videoUri)
        fragment.arguments = bundle
        return fragment
    }

    lateinit var imageUri: String
    lateinit var videoUri: String
    override fun initExtra() {
        super.initExtra()
        imageUri = arguments?.getString("imageUri")!!
        videoUri = arguments?.getString("videoUri")!!
    }

    override fun initViews(view: View) {
        start_tv.setOnClickListener {
            videoView.start()
            start_tv.visibility = View.GONE
        }
        videoView.setOnCompletionListener {
            it.seekTo(0)
            start_tv.visibility = View.VISIBLE
        }
        video_big_iv.setOnClickListener {
            val intent = Intent(mContext, GoodsVideoActivity::class.java)
            intent.putExtra("VideoUri",videoUri)
            intent.putExtra("ImageUri",imageUri)
            startActivity(intent)
        }

    }

    override fun initDatas() {
    }

}