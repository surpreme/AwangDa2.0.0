package com.aite.awangdalibrary.ui.activity.findpassword

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.StatusBarUtils
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.activity_login_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class FindPassWordUIAdapter(context: Context?) : BaseRecyAdapter<FindPassWordUIBean>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_hinttitle_edit_recy, parent, false))
            VIEW_TYPE_CONTENT_TWO -> viewHolder = TWOContentViewHolder(inflater.inflate(R.layout.item_phone_code_edit_title, parent, false))
            VIEW_TYPE_TOOLBAR -> viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
            VIEW_TYPE_BOTTOM -> viewHolder = BottomViewHolder(inflater.inflate(R.layout.item_text, parent, false))

        }
        return viewHolder!!
    }

    override fun fixData(mMAp: Map<String, String>) {
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].title != null && mDatas[position].title != "") {
            VIEW_TYPE_CONTENT
        } else if (mDatas[position].centerBarTitle != null && mDatas[position].centerBarTitle != "")
            VIEW_TYPE_TOOLBAR
        else if (mDatas[position].bottomStr != null && mDatas[position].bottomStr != "")
            VIEW_TYPE_BOTTOM
        else if (mDatas[position].phoneCode != null && mDatas[position].phoneCode != "")
            VIEW_TYPE_CONTENT_TWO
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            //toolbar
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = mDatas?.get(position)?.centerBarTitle
                (holder as ToolBarViewHolder).tv_title?.setTextColor(Color.WHITE)
                (holder as ToolBarViewHolder).iv_back?.setColorFilter(Color.WHITE)
                (holder as ToolBarViewHolder).itemView.setPadding(0, StatusBarUtils.getHeight(context!!), 0, 0)
                val params: ViewGroup.LayoutParams = (holder as ToolBarViewHolder).itemView.layoutParams as ViewGroup.LayoutParams
                params.height = (params.height + StatusBarUtils.getHeight(context))
                (holder as ToolBarViewHolder).itemView.layoutParams = params
                (holder as ToolBarViewHolder).itemView.background = context?.resources?.getDrawable(R.drawable.main_theme_background)!!

                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }

            }
            //text+edit
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).itemView.setBackgroundColor(Color.parseColor("#F0F0F0"))
                (holder as ContentViewHolder).tv_hint_title?.text = mDatas[position].title
                (holder as ContentViewHolder).content_edit?.hint = mDatas[position].editHint
                if (position == 1)
                    (holder as ContentViewHolder).content_edit?.inputType = InputType.TYPE_CLASS_PHONE
                else
                    (holder as ContentViewHolder)
                            .content_edit?.inputType = InputType.TYPE_CLASS_TEXT
                val params: ViewGroup.LayoutParams = (holder as ContentViewHolder).itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 32f)
                (holder as ContentViewHolder).itemView.layoutParams = params

                (holder as ContentViewHolder).content_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        mDatas[position].outStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }
            //text+edit 验证码
            VIEW_TYPE_CONTENT_TWO -> {
                (holder as TWOContentViewHolder).itemView.setBackgroundColor(Color.parseColor("#F0F0F0"))
                (holder as TWOContentViewHolder).tv_title?.text = mDatas[position].phoneTitle
                (holder as TWOContentViewHolder).phone_edit?.hint = mDatas[position].phoneEditHint
                (holder as TWOContentViewHolder).send_code_tv?.text = mDatas[position].phoneCode
                (holder as TWOContentViewHolder).phone_edit?.inputType = InputType.TYPE_CLASS_PHONE
                val params: ViewGroup.LayoutParams = (holder as TWOContentViewHolder).itemView.layoutParams as ViewGroup.LayoutParams
                params.height = SystemUtil.dp2px(context, 32f)
                (holder as TWOContentViewHolder).itemView.layoutParams = params

                (holder as TWOContentViewHolder).phone_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        mDatas[position].phoneEditOutStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }
            //提交
            VIEW_TYPE_BOTTOM -> {
                (holder as BottomViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                (holder as BottomViewHolder).tvBottom?.text = mDatas[position].bottomStr
                (holder as BottomViewHolder).tvBottom?.textSize = 12f
                (holder as BottomViewHolder).tvBottom?.setTextColor(Color.WHITE)
                (holder as BottomViewHolder).tvBottom?.background = context.resources?.getDrawable(R.drawable.main_theme_background_round)!!
                (holder as BottomViewHolder).tvBottom?.setOnClickListener {
                    if (mOnCommitInterface != null) mOnCommitInterface?.commit(mDatas, it)
                }
            }
        }
    }

    var mOnCommitInterface: OnClickLstenerInterface.OnCommitInterfaces<FindPassWordUIBean>? = null


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var content_edit: EditText? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            content_edit = itemView.findViewById(R.id.password_edit)
        }
    }

    class TWOContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null
        var send_code_tv: TextView? = null
        var phone_edit: EditText? = null

        init {
            send_code_tv = itemView.findViewById(R.id.send_code_tv)
            tv_title = itemView.findViewById(R.id.tv_title)
            phone_edit = itemView.findViewById(R.id.phone_edit)
        }
    }
}