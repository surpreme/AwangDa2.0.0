package com.aite.awangdalibrary.ui.fragment.goodsimage

import android.os.Bundle
import android.view.View
import com.aite.awangdalibrary.R
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_goods_imges.*
import me.goldze.mvvmhabit.utils.ImageUtils


/**

 * @Auther: valy

 * @datetime: 2020/4/7

 * @desc:

 */
class GoodsImageFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_goods_imges
    fun bulider(uri: String, content: String): GoodsImageFragment {
        val fragment = GoodsImageFragment()
        val bundle = Bundle()
        bundle.putString("uri", uri)
        bundle.putString("content", content)
        fragment.arguments = bundle
        return fragment
    }

    lateinit var imageUri: String
    lateinit var contentStr: String
    override fun initExtra() {
        super.initExtra()
        imageUri = arguments?.getString("uri")!!
        contentStr = arguments?.getString("content")!!
    }

    override fun initViews(view: View) {
        Glide.with(view).load(imageUri).into(image)
        content_tv.text = contentStr
    }

    override fun initDatas() {
    }

}