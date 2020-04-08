package com.aite.a.adapter

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.bean.FixPasswordUIBean
import com.jiananshop.a.R
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/5

 * @desc:

 */
open class FixPasswordUIRecyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val mContext: Context
    private val listFirst: List<FixPasswordUIBean>?
    private val inflater: LayoutInflater
    private val VIEW_TYPE_CONTENT: Int = 1
    private val VIEW_TYPE_BOTTOM: Int = 2
    private val VIEW_TYPE_TOOLBAR: Int = 3

    constructor(mContext: Context?, listFirst: List<FixPasswordUIBean>?) : super() {
        this.mContext = mContext!!
        this.listFirst = listFirst
        this.inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_hinttitle_edit_recy, parent, false))
            VIEW_TYPE_BOTTOM -> viewHolder = BottomViewHolder(inflater.inflate(R.layout.text_layout, parent, false))
            VIEW_TYPE_TOOLBAR -> viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return listFirst?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (listFirst?.get(position)?.bottomStr != null && listFirst[position].bottomStr != "")
            VIEW_TYPE_BOTTOM
        else if (listFirst?.get(position)?.hintTitle != null && listFirst[position].hintTitle != "")
            VIEW_TYPE_CONTENT
        else if (listFirst?.get(position)?.centerBarTitle != null && listFirst[position].centerBarTitle != "")
            VIEW_TYPE_TOOLBAR
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = listFirst?.get(position)?.centerBarTitle
                (holder as ToolBarViewHolder).itemView.setBackgroundColor(mContext.resources.getColor(R.color.white))

                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }

            }
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).tv_hint_title?.text = listFirst?.get(position)?.hintTitle
                (holder as ContentViewHolder).password_edit?.hint = listFirst?.get(position)?.hintContent
                (holder as ContentViewHolder).password_edit?.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                //设置密码不可见
                (holder as ContentViewHolder).password_edit?.transformationMethod = PasswordTransformationMethod()

                (holder as ContentViewHolder).password_edit?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        listFirst?.get(position)?.outStr = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }
            VIEW_TYPE_BOTTOM -> {
                (holder as BottomViewHolder).tvBottom?.text = listFirst?.get(position)?.bottomStr
                (holder as BottomViewHolder).tvBottom?.setTextColor(mContext.resources.getColor(R.color.white))
                (holder as BottomViewHolder).tvBottom?.setBackgroundColor(mContext.resources.getColor(R.color.orangeff6c00))
                (holder as BottomViewHolder).tvBottom?.setOnClickListener {
                    if (mOnCommitInterface != null) mOnCommitInterface?.commit(this.listFirst)
                }
            }
        }
    }



    var mOnCommitInterface: OnClickLstenerInterface.OnCommitInterface<FixPasswordUIBean>? = null
    //    var onclickInterface: OnClickLstenerInterface.OnRecyClickInterface? = null
    var toolBarClickInterface: OnClickLstenerInterface.OnToolBarClickInterface? = null

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_hint_title: TextView? = null
        var password_edit: EditText? = null

        init {
            tv_hint_title = itemView.findViewById(R.id.tv_hint_title)
            password_edit = itemView.findViewById(R.id.password_edit)
        }
    }

    class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBottom: TextView? = null

        init {
            tvBottom = itemView.findViewById(R.id.textView)
        }
    }

    class ToolBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_back: ImageView? = null
        var tv_title: TextView? = null
        var tv_right_title: TextView? = null

        init {
            iv_back = itemView.findViewById(R.id.iv_back)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_right_title = itemView.findViewById(R.id.tv_right_title)
        }
    }
}