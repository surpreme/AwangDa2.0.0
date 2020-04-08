package com.aite.a.activity.li.activity.addaddress

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.activity.li.util.LogUtils
import com.aite.a.activity.li.util.TextColorUtils
import com.bumptech.glide.Glide
import com.jiananshop.a.R
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/5

 * @desc:

 */
open class AddAddressUIRecyAdapter(context: Context?) : BaseRecyAdapter<AddAddressUIBean>(context) {
    private val VIEW_TYPE_CONTENT_THREE: Int = 5
    private val VIEW_TYPE_CONTENT_FOUR: Int = 6


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_hinttitle_edit_recy, parent, false))
            VIEW_TYPE_CONTENT_TWO -> viewHolder = TWOContentViewHolder(inflater.inflate(R.layout.item_phone_number, parent, false))
            VIEW_TYPE_CONTENT_THREE -> viewHolder = ThreeContentViewHolder(inflater.inflate(R.layout.item_address_click_location, parent, false))
            VIEW_TYPE_CONTENT_FOUR -> viewHolder = FourContentViewHolder(inflater.inflate(R.layout.item_hinttitle_tv_recy, parent, false))
            VIEW_TYPE_BOTTOM -> viewHolder = BottomViewHolder(inflater.inflate(R.layout.text_layout, parent, false))
            VIEW_TYPE_TOOLBAR -> viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
        }
        return viewHolder!!
    }


    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].bottomStr != null && mDatas[position].bottomStr != "")
            VIEW_TYPE_BOTTOM
        else if (mDatas.get(position)?.hintTitle != null && mDatas[position].hintTitle != "") {
            VIEW_TYPE_CONTENT
        } else if (mDatas?.get(position)?.phoneAddressChoiceBean != null)
            VIEW_TYPE_CONTENT_TWO
        else if (mDatas?.get(position)?.clickLocationBean != null)
            VIEW_TYPE_CONTENT_THREE
        else if (mDatas?.get(position)?.cityChoiceBean != null)
            VIEW_TYPE_CONTENT_FOUR
        else if (mDatas?.get(position).centerBarTitle != null && mDatas[position].centerBarTitle != "")
            VIEW_TYPE_TOOLBAR
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            //toolbar
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = mDatas?.get(position)?.centerBarTitle
                (holder as ToolBarViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)

                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }

            }
            //text+edit
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                if (position != mDatas.size - 2) {
                    (holder as ContentViewHolder).tv_hint_title?.text =
                            TextColorUtils.setSpannableStringBuilder(mDatas.get(position)?.hintTitle, 1, Color.RED, Color.BLACK)
                } else {
                    (holder as ContentViewHolder).tv_hint_title?.text = mDatas.get(position)?.hintTitle
                }

                (holder as ContentViewHolder).password_edit?.hint = mDatas.get(position).hintContent
                if (position == 2)
                    (holder as ContentViewHolder).password_edit?.inputType = InputType.TYPE_CLASS_PHONE
                else
                    (holder as ContentViewHolder).password_edit?.inputType = InputType.TYPE_CLASS_TEXT
                (holder as ContentViewHolder).password_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        mDatas[position].outStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }
            //定位
            VIEW_TYPE_CONTENT_THREE -> {
                (holder as ThreeContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as ThreeContentViewHolder).tv_hint_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas.get(position).clickLocationBean.title, 1, Color.RED, Color.BLACK)
                (holder as ThreeContentViewHolder).address_tv?.text = mDatas.get(position).clickLocationBean.content
                (holder as ThreeContentViewHolder).itemView.setOnClickListener { view ->
                    if (clickLstenerInterface != null) clickLstenerInterface!!.getPosition(view, position)
                }
            }
            //城市选择
            VIEW_TYPE_CONTENT_FOUR -> {
                (holder as FourContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as FourContentViewHolder).tv_hint_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas.get(position).cityChoiceBean.title, 1, Color.RED, Color.BLACK)
                (holder as FourContentViewHolder).content_tv?.text = mDatas.get(position).cityChoiceBean.content
                (holder as FourContentViewHolder).itemView.setOnClickListener { view ->
                    if (clickLstenerInterface != null) clickLstenerInterface!!.getPosition(view, position)
                }
            }
            //手机号
            VIEW_TYPE_CONTENT_TWO -> {
                (holder as TWOContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as TWOContentViewHolder).area_name_tv?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas.get(position).phoneAddressChoiceBean.title, 1, Color.RED, Color.BLACK)
                (holder as TWOContentViewHolder).area_name_tv?.setOnClickListener { view ->
                    if (clickLstenerInterface != null) clickLstenerInterface!!.getPosition((holder as TWOContentViewHolder).area_icon_iv!!, position)
                }
                Glide.with(context).load(mDatas.get(position).phoneAddressChoiceBean.icon).into((holder as TWOContentViewHolder).area_icon_iv!!)
                (holder as TWOContentViewHolder).area_code_tv?.setOnClickListener { view ->
                    if (clickLstenerInterface != null) clickLstenerInterface!!.getPosition((holder as TWOContentViewHolder).area_icon_iv!!, position)
                }
                (holder as TWOContentViewHolder).area_icon_iv?.setOnClickListener { view ->
                    if (clickLstenerInterface != null) clickLstenerInterface!!.getPosition((holder as TWOContentViewHolder).area_icon_iv!!, position)
                }
                if (position == 2)
                    (holder as TWOContentViewHolder).phone_edit?.inputType = InputType.TYPE_CLASS_PHONE
                else
                    (holder as TWOContentViewHolder).phone_edit?.inputType = InputType.TYPE_CLASS_TEXT
                (holder as TWOContentViewHolder).area_code_tv?.text = mDatas.get(position).phoneAddressChoiceBean.code
                (holder as TWOContentViewHolder).phone_edit?.hint = mDatas.get(position).phoneAddressChoiceBean.content
//                (holder as TWOContentViewHolder).phone_edit?.inputType = InputType.TYPE_CLASS_PHONE
                (holder as TWOContentViewHolder).phone_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        mDatas.get(position).phoneAddressChoiceBean.outStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }
            //提交
            VIEW_TYPE_BOTTOM -> {
                (holder as BottomViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as BottomViewHolder).tvBottom?.text = mDatas?.get(position)?.bottomStr
                (holder as BottomViewHolder).tvBottom?.setTextColor(context?.resources?.getColor(R.color.white)!!)
                (holder as BottomViewHolder).tvBottom?.setBackgroundColor(context?.resources?.getColor(R.color.red)!!)
                (holder as BottomViewHolder).tvBottom?.setOnClickListener {
                    if (mOnCommitInterface != null) mOnCommitInterface?.commit(mDatas)
                }
            }
        }
    }


    var mOnCommitInterface: OnClickLstenerInterface.OnCommitInterface<AddAddressUIBean>? = null


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var password_edit: EditText? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            password_edit = itemView.findViewById(R.id.password_edit)
        }
    }

    class ThreeContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var address_tv: TextView? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            address_tv = itemView.findViewById(R.id.address_tv)
        }
    }

    class FourContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var content_tv: TextView? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            content_tv = itemView.findViewById(R.id.content_tv)
        }
    }

    class TWOContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var area_name_tv: TextView? = null
        var area_code_tv: TextView? = null
        var area_icon_iv: ImageView? = null
        var phone_edit: EditText? = null

        init {
            area_name_tv = itemView.findViewById(R.id.area_name_tv)
            area_code_tv = itemView.findViewById(R.id.area_code_tv)
            area_icon_iv = itemView.findViewById(R.id.area_icon_iv)
            phone_edit = itemView.findViewById(R.id.phone_edit)
        }
    }

    class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBottom: TextView? = null

        init {
            tvBottom = itemView.findViewById(R.id.textView)
        }
    }

    /**
     * x icon
     * y title
     * z code
     */
    override fun fixData(mMAp: Map<String, String>) {
        mDatas[2].phoneAddressChoiceBean.icon = mMAp["x"]
        mDatas[2].phoneAddressChoiceBean.title = "*${mMAp["y"]}"
        mDatas[2].phoneAddressChoiceBean.code = mMAp["z"]
        notifyDataSetChanged()
    }

}