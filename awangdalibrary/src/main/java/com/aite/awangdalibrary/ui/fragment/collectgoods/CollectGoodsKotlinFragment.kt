package com.aite.awangdalibrary.ui.fragment.collectgoods

import android.content.Intent
import android.graphics.Rect
import android.view.View
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.coupon.CollectGoodsAdapter
import com.aite.awangdalibrary.ui.activity.coupon.CollectGoodsContract
import com.aite.awangdalibrary.ui.activity.coupon.CollectGoodsPresenter
import com.aite.awangdalibrary.ui.activity.productdetails.ProductDetailsKotlinActivity
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
class CollectGoodsKotlinFragment : BaseMVPSmartListFragment<CollectGoodsContract.View, CollectGoodsPresenter, CollectGoodsBean>(), CollectGoodsContract.View {
    override fun getSmartLayoutId(): Int = R.id.smartlayout
    override fun getRecyclerViewId(): Int = R.id.recycler_smart_view
    override fun getLayoutResId(): Int = R.layout.recycler_smart_layout

    override fun setAdapter(): BaseRecyAdapter<CollectGoodsBean>? {
        val mCollectGoodsAdapter = CollectGoodsAdapter(mContext)
        mCollectGoodsAdapter.mOnClickCollectInterface = object : CollectGoodsAdapter.OnClickCollectInterface {
            override fun onShop(v: View, s: String) {
                ARouter.getInstance().build("/jn/ShopHomeActivity").withString("store_id", s).navigation()

            }

            override fun onGoods(v: View, s: String) {
                val intent = Intent(mContext, ProductDetailsKotlinActivity::class.java)
                intent.putExtra("goods_id", s)
                intent.putExtra("key", BaseApp.getKey())
                startActivity(intent)
            }

            override fun onDelete(v: View, s: String) {
                val mTalkDialogFragment = TalkDialogFragment().newInstance("取消收藏此商品", "确定")
                mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                    mPresenter?.cancelCollect(BaseApp.getKey(), s)

                }
                mTalkDialogFragment?.show(childFragmentManager, "deleteTalkDialog")
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
        mPresenter?.getDatas(mCurrentPage, "goods", BaseApp.getKey(), "zh_cn")

    }


    override fun getDatasSuccess(list: List<CollectGoodsBean>, ishaseMore: Boolean) {
        isMore = ishaseMore
        appendDatas(list)

    }

    override fun cancelCollectSuccess() {
        refresh()


    }


}