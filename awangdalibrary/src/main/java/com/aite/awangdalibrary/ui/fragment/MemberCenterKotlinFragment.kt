package com.aite.awangdalibrary.ui.fragment

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseMVPListFragment
import com.valy.baselibrary.utils.SystemUtil
import java.util.ArrayList
import kotlin.math.min

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
class MemberCenterKotlinFragment : BaseMVPListFragment<MemberCenterContract.View, MemberCenterPresenter, MemberCenterUIBean>() {
    override fun initViews(view: View) {
        super.initViews(view)

    }

    override fun initDatas() {
        for (index in 0..4) {
            val f = MemberCenterUIBean()
            when (index) {
                0 -> {
                    val mInformationBean = MemberCenterUIBean.InformationBean()
                    mInformationBean.name = "相隔远川（16138443）"
                    mInformationBean.iconUrl = "相隔远川（16138443）"
                    mInformationBean.integral = "7569"
                    mInformationBean.gold = "23"
                    mInformationBean.balance = "￥159"
                    f.informationBean = mInformationBean

                }
                1 -> {
                    f.tips = "我的订单"

                }
                2 -> {
                    val memberlist = mutableListOf<MemberCenterUIBean.OrderBean>()
                    for (postion in 0..4) {
                        val t = MemberCenterUIBean.OrderBean()
                        when (postion) {
                            0 -> {
                                t.iconId = R.mipmap.allorder_logo_aw
                                t.name = "全部订单"
                            }
                            1 -> {
                                t.iconId = R.mipmap.unsendgoods_logo
                                t.name = "待发货"
                            }
                            2 -> {
                                t.iconId = R.mipmap.ungetgoods_logo
                                t.name = "待收货"
                            }
                            3 -> {
                                t.iconId = R.mipmap.untalkgoods_logo
                                t.name = "待评价"
                            }
                            4 -> {
                                t.iconId = R.mipmap.allorder_logo_aw
                                t.name = "退款/售后"
                            }
                        }
                        memberlist.add(t)
                    }
                    f.orderBean = memberlist.toList() as MutableList<MemberCenterUIBean.OrderBean>
                    f.type = 1

                }
                3 -> {
                    f.tips = "会员中心"


                }
                4 -> {
                    val memberlist = mutableListOf<MemberCenterUIBean.OrderBean>()
                    for (postion in 0..7) {
                        val t = MemberCenterUIBean.OrderBean()
                        when (postion) {
                            0 -> {
                                t.iconId = R.mipmap.address_logoaw
                                t.name = "我的地址"
                            }
                            1 -> {
                                t.iconId = R.mipmap.minetalk_logoaw
                                t.name = "我的评价"
                            }
                            2 -> {
                                t.iconId = R.mipmap.lookallgoodsbook_logo
                                t.name = "我的足迹"
                            }
                            3 -> {
                                t.iconId = R.mipmap.collect_logo_aw
                                t.name = "我的收藏"
                            }
                            4 -> {
                                t.iconId = R.mipmap.sigh_logoaw
                                t.name = "我的信息"
                            }
                            5 -> {
                                t.iconId = R.mipmap.safety_logoaw
                                t.name = "账户安全"
                            }
                            6 -> {
                                t.iconId = R.mipmap.coupon_logoaw
                                t.name = "优惠券"
                            }
                            7 -> {
                                t.iconId = R.mipmap.coupon_logoaw
                                t.name = "领券中心"
                            }

                        }
                        memberlist.add(t)
                    }
                    f.orderBean = memberlist.toList() as MutableList<MemberCenterUIBean.OrderBean>
                    f.type = 2

                }
            }
            mDatasList.add(f)

        }
        appendDatas()

    }

    override fun setAdapter(): BaseRecyAdapter<MemberCenterUIBean>? {
        val mMemberCenterAdapter = MemberCenterAdapter(mContext!!)
        return mMemberCenterAdapter
    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == 0 || position == 2)
                rect.bottom = SystemUtil.dp2px(mContext, 15f)
//            } else if (position == 2) {
//                rect.bottom = SystemUtil.dp2px(mContext, 50f)
//            }
        }

        override fun doRule(position: Int, rect: Rect) {
//            if (position == 0 || position == 3)
                rect.bottom = rect.top

        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_view

    override fun onDestroys() {
    }

    override fun getLayoutResId(): Int = R.layout.recycler_layout


}