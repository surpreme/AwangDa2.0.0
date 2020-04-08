package com.aite.awangdalibrary.ui.fragment.collectstore

import android.graphics.Rect
import android.view.View
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.coupon.CollectStoreAdapter
import com.aite.awangdalibrary.ui.activity.coupon.CollectStoreContract
import com.aite.awangdalibrary.ui.activity.coupon.CollectStorePresenter
import com.alibaba.android.arouter.launcher.ARouter
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.basekotlin.BaseMVPSmartListFragment
import com.valy.baselibrary.dialogfragment.TalkDialogFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/19

 * @desc:

 */
class CollectStoreKotlinFragment : BaseMVPSmartListFragment<CollectStoreContract.View, CollectStorePresenter, CollectStoreBean>(), CollectStoreContract.View {
    override fun getSmartLayoutId(): Int = R.id.smartlayout
    override fun getRecyclerViewId(): Int = R.id.recycler_smart_view
    override fun getLayoutResId(): Int = R.layout.recycler_smart_layout


    override fun setAdapter(): BaseRecyAdapter<CollectStoreBean>? {
        val mCollectGoodsAdapter = CollectStoreAdapter(mContext)
        mCollectGoodsAdapter.tClickInterface = OnClickLstenerInterface.OnRecyClicksTInterface { v, s ->
            ARouter.getInstance().build("/jn/ShopHomeActivity").withString("store_id", s).navigation()

        }
        mCollectGoodsAdapter.mOnDeleteClickListener = object : CollectStoreAdapter.OnDeleteClickListener {
            override fun onClick(v: View, s: String) {
                val mTalkDialogFragment = TalkDialogFragment().newInstance("取消收藏此店铺", "确定")
                mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                    mPresenter?.cancelCollect(BaseApp.getKey(), s)

                }
                mTalkDialogFragment?.show(childFragmentManager, "deleteTalkDialogCollectStoreKotlinFragment")
            }
        }
        return mCollectGoodsAdapter
    }

    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {

        }

        override fun doRule(position: Int, rect: Rect) {
            rect.left = rect.right
        }
    }

    override fun getPageDatas(mCurrentPage: Int) {
        super.getPageDatas(mCurrentPage)
        mPresenter?.getDatas(mCurrentPage, "store", BaseApp.getKey(), "zh_cn")

    }


    override fun getDatasSuccess(list: List<CollectStoreBean>, ishaseMore: Boolean) {
        isMore = ishaseMore
        appendDatas(list)
    }

    override fun cancelCollectSuccess() {
        refresh()
    }


}