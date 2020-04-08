package com.aite.awangdalibrary.ui.activity.transfer

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.utils.TextColorUtils
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.activity_login_kotlin.*

/**
 * @Auther: valy
 * @datetime: 2020/3/13
 * @desc:
 */
class TransferUIAdapter(context: Context?) : BaseRecyAdapter<TransferUIBean>(context) {
    val VIEW_TYPE_CONTENT_THREE: Int = 5
    val VIEW_TYPE_CONTENT_FOUR: Int = 6
    val VIEW_TYPE_CONTENT_FIVE: Int = 7
    val VIEW_TYPE_CONTENT_SIX: Int = 8

    override fun fixData(mMAp: Map<String, String>) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT ->
                viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_hinttitle_edit_recy, parent, false))
            VIEW_TYPE_TOOLBAR ->
                viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
            VIEW_TYPE_CONTENT_TWO ->
                viewHolder = TextViewHolder(inflater.inflate(R.layout.item_text, parent, false))
            VIEW_TYPE_CONTENT_THREE ->
                viewHolder = InformationHolder(inflater.inflate(R.layout.item_transfer_information, parent, false))
            VIEW_TYPE_CONTENT_FOUR ->
                viewHolder = ChoiceTypeHolder(inflater.inflate(R.layout.item_transfer_type, parent, false))
            VIEW_TYPE_CONTENT_FIVE ->
                viewHolder = FiveContentViewHolder(inflater.inflate(R.layout.item_transfer_password, parent, false))
            VIEW_TYPE_CONTENT_SIX ->
                viewHolder = SixContentViewHolder(inflater.inflate(R.layout.item_transfer_send_phone_code, parent, false))

        }
        return viewHolder!!
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].outBean != null)
            VIEW_TYPE_CONTENT
        else if (mDatas[position].tips != null && mDatas[position].tips != "") {
            VIEW_TYPE_CONTENT_TWO
        } else if (mDatas[position].informationBean != null)
            VIEW_TYPE_CONTENT_THREE
        else if (mDatas[position].chocieTypeBean != null)
            VIEW_TYPE_CONTENT_FOUR
        else if (mDatas[position].passwordBean != null)
            VIEW_TYPE_CONTENT_FIVE
        else if (mDatas[position].phoneUiBean != null)
            VIEW_TYPE_CONTENT_SIX
        else if (mDatas[position].centerBarTitle != null && mDatas[position].centerBarTitle != "")
            VIEW_TYPE_TOOLBAR
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            //toolbar
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = mDatas[position].centerBarTitle
                (holder as ToolBarViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)

                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }

            }
            //text+edit
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                //背景图
                val params: ViewGroup.LayoutParams = (holder as ContentViewHolder).itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 40f)
                (holder as ContentViewHolder).itemView.layoutParams = params
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
//                if (position != mDatas.size - 2) {
                holder.tv_hint_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas[position].outBean.title, 1, Color.RED, Color.DKGRAY)
//                } else {
//                    (holder as ContentViewHolder).tv_hint_title?.text = mDatas[position].outBean.title
//                    (holder as ContentViewHolder).tv_hint_title?.setTextColor(Color.DKGRAY)
//                }

                (holder as ContentViewHolder).content_edit?.hint = mDatas[position].outBean.hint
                (holder as ContentViewHolder).content_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        mDatas[position].outBean.outStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }

            //tips
            VIEW_TYPE_CONTENT_TWO -> {
                (holder as TextViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                holder.itemView.setPadding(10, 0, 0, 0)
                (holder as TextViewHolder).textView?.text = mDatas[position].tips
                (holder as TextViewHolder).textView?.gravity = Gravity.CENTER_VERTICAL
                (holder as TextViewHolder).textView?.setTextColor(context.resources?.getColor(R.color.orangeDark)!!)

            }
            //账号详细信息
            VIEW_TYPE_CONTENT_THREE -> {
                (holder as InformationHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as InformationHolder).balance_price_tv?.text = mDatas[position].informationBean.balance
                (holder as InformationHolder).gold_price_tv?.text = mDatas[position].informationBean.gold
                (holder as InformationHolder).integral_price_tv?.text = mDatas[position].informationBean.integral
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
            }
            VIEW_TYPE_CONTENT_FOUR -> {
                (holder as ChoiceTypeHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as ChoiceTypeHolder).tv_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas[position].chocieTypeBean.title, 1, Color.RED, Color.DKGRAY)
//                mDatas[position].chocieTypeBean.title
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
                val params: ViewGroup.LayoutParams = holder.itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 40f)
                holder.itemView.layoutParams = params
            }
            VIEW_TYPE_CONTENT_FIVE -> {
                (holder as FiveContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as FiveContentViewHolder).tv_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas[position].passwordBean.title, 1, Color.RED, Color.DKGRAY)

//                mDatas[position].passwordBean.title
                (holder as FiveContentViewHolder).password_eyes_edit?.hint = mDatas[position].passwordBean.hint
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
                val params: ViewGroup.LayoutParams = holder.itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 40f)
                holder.itemView.layoutParams = params
            }
            VIEW_TYPE_CONTENT_SIX -> {
                (holder as SixContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                holder.tv_title?.text =
                        TextColorUtils.setSpannableStringBuilder(mDatas[position].phoneUiBean.title, 1, Color.RED, Color.DKGRAY)

//                mDatas[position].phoneUiBean.title
                holder.phone_tv?.text = mDatas[position].phoneUiBean.phone
                holder.send_code_tv?.text = mDatas[position].phoneUiBean.sendText
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
                val params: ViewGroup.LayoutParams = holder.itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 40f)
                holder.itemView.layoutParams = params
            }
        }
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    class InformationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var balance_price_tv: TextView? = null//余额
        var gold_price_tv: TextView? = null//金币
        var integral_price_tv: TextView? = null//积分

        init {
            balance_price_tv = itemView.findViewById(R.id.balance_price_tv)
            integral_price_tv = itemView.findViewById(R.id.integral_price_tv)
            gold_price_tv = itemView.findViewById(R.id.gold_price_tv)
        }
    }

    class ChoiceTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null//余额
        var choice_rg: RadioGroup? = null//金币
        var points_rb: RadioButton? = null//积分
        var balance_rb: RadioButton? = null//积分
        var gold_rt: RadioButton? = null//积分

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            choice_rg = itemView.findViewById(R.id.choice_rg)
            points_rb = itemView.findViewById(R.id.points_rb)
            balance_rb = itemView.findViewById(R.id.balance_rb)
            gold_rt = itemView.findViewById(R.id.gold_rt)
        }
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var content_edit: EditText? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            content_edit = itemView.findViewById(R.id.password_edit)
        }
    }

    class FiveContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null
        var password_eyes_edit: TextInputEditText? = null

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            password_eyes_edit = itemView.findViewById(R.id.password_eyes_edit)
        }
    }

    class SixContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null
        var phone_tv: TextView? = null
        var send_code_tv: TextView? = null

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            phone_tv = itemView.findViewById(R.id.phone_tv)
            send_code_tv = itemView.findViewById(R.id.send_code_tv)
        }
    }

}