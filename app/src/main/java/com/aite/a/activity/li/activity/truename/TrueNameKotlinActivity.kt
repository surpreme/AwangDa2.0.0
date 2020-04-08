package com.aite.a.activity.li.activity.truename

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.APPSingleton
import com.aite.a.activity.ChoiceAddressActivity
import com.valy.baselibrary.bean.BaseConstant
import com.aite.a.activity.li.activity.login.AreaCodeAdapter
import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.activity.li.util.ActivityManager
import com.aite.a.activity.li.util.LogUtils
import com.aite.a.activity.li.util.PopupWindowUtil
import com.aite.a.adapter.BaseTextViewRecyAdapter
import com.aite.a.base.Mark
import com.aite.a.utils.ChoiceUtils
import com.aite.a.utils.FileUtils
import com.aite.a.utils.SoftKeyboardUtil
import com.aite.a.utils.ToastUtils
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.bumptech.glide.Glide
import com.jiananshop.a.R
import com.valy.baselibrary.dialogfragment.UpdatePhotosFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface.OnThingClickInterface
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.activity_true_name_kotlin.*
import kotlinx.android.synthetic.main.include_true_name_kotlin.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**

 * @Auther: valy

 * @datetime: 2020/3/16

 * @desc:

 */
class TrueNameKotlinActivity : BaseMVPActivity<TrueNameContract.View, TrueNamePresenter>(), TrueNameContract.View {
    var areaCode = ArrayList<AreaCodeBean.ListBean>()
    var codeTag: String? = null
    var mSelectedPictrueUri: Uri? = null
    var mSelectedPictrueUri2: Uri? = null
    var mSelectedPictrueUri3: Uri? = null
    var mSelectedPictrueUri4: Uri? = null
    val REQUEST_CAMERA1 = 781
    val REQUEST_CAMERA2 = 782
    val REQUEST_CAMERA3 = 783
    val REQUEST_CAMERA4 = 784
    val REQUEST_CODE_CHOOSE_IMAGE = 181
    val REQUEST_CODE_CHOOSE_IMAGE2 = 182
    val REQUEST_CODE_CHOOSE_IMAGE3 = 183
    val REQUEST_CODE_CHOOSE_IMAGE4 = 184
    var gender = "0"
    var mImageUri: Uri? = null
    override fun onDestroys() {
    }

    override fun getLayoutId(): Int = R.layout.activity_true_name_kotlin

    override fun initViews() {
        initToolBar("实名认证")
        setStatusBar(0)
    }

    /**
    key	post	字符串	必须			会员登录key
    auth_name	post	字符串	必须			真实姓名
    code	post	字符串	必须			国际区号
    mobile	post	字符串	必须			手机号码
    auth_sex	post	字符串	必须	0		性别 0男 1女
    auth_area_info	post	字符串	必须			所在地区 |分隔)
    auth_address	post	字符串	必须			地址(所在地区+详细地址)
    auth_card	post	字符串	必须			身份证号
    id_card_img	post	字符串	必须			身份证图片（正面）
    auth_fm_imgurl	post	字符串	必须			手持身份证（反面）
    auth_sc_imgurl	post	字符串	必须			手持身份证
    business_license_img	post	字符串	必须			营业执照图片
     */
    override fun onClick() {
        super.onClick()
        commit_btn.setOnClickListener {
            val builder = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
            builder.addFormDataPart("key", Mark.State.UserKey)
            builder.addFormDataPart("auth_name", true_name_edit.text.toString())
            builder.addFormDataPart("code", codeTag)
            builder.addFormDataPart("mobile", phone_edit.text.toString())
            builder.addFormDataPart("auth_sex", gender)
            builder.addFormDataPart("auth_area_info", area_tv.text.toString())
            builder.addFormDataPart("auth_address", area_tv.text.toString() + all_area_edit.text.toString())
            builder.addFormDataPart("auth_card", identification_number_edit.text.toString())
            for (dex in 1..4) {
                var key = ""
                when (dex) {
                    1 -> {
                        key = "id_card_img"
                    }
                    2 -> {
                        key = "auth_fm_imgurl"
                    }
                    3 -> {
                        key = "auth_sc_imgurl"
                    }
                    4 -> {
                        key = "business_license_img"
                    }
                }
                if (mImgsList[dex] != null) {
                    builder.addFormDataPart(key, mImgsList[dex])
                }
            }
            mPresenter?.commit(builder.build().parts())
        }
        people_book_first_tv.setOnClickListener {
            choiceOne(1, REQUEST_CAMERA1, REQUEST_CODE_CHOOSE_IMAGE)
        }
        people_book_first_img.setOnClickListener {
            choiceOne(1, REQUEST_CAMERA1, REQUEST_CODE_CHOOSE_IMAGE)
        }
        people_book_second_tv.setOnClickListener {
            choiceOne(2, REQUEST_CAMERA2, REQUEST_CODE_CHOOSE_IMAGE2)
        }
        people_book_second_img.setOnClickListener {
            choiceOne(2, REQUEST_CAMERA2, REQUEST_CODE_CHOOSE_IMAGE2)
        }
        face_card_img.setOnClickListener {
            choiceOne(3, REQUEST_CAMERA3, REQUEST_CODE_CHOOSE_IMAGE3)
        }
        face_card_tv.setOnClickListener {
            choiceOne(3, REQUEST_CAMERA3, REQUEST_CODE_CHOOSE_IMAGE3)
        }
        face_people_book_first_img.setOnClickListener {
            choiceOne(4, REQUEST_CAMERA4, REQUEST_CODE_CHOOSE_IMAGE4)
        }
        business_license_upload_tv.setOnClickListener {
            choiceOne(4, REQUEST_CAMERA4, REQUEST_CODE_CHOOSE_IMAGE4)
        }
        area_name_tv.setOnClickListener {
            showPop()
        }
        area_code_tv.setOnClickListener {
            showPop()
        }
        area_icon_iv.setOnClickListener {
            showPop()

        }
        area_cl.setOnClickListener {
            val intent = Intent(mContext, ChoiceAddressActivity::class.java)
            startActivityForResult(intent, BaseConstant.RESULT_CODE.REQUEST_ETRAS)
        }
        gender_cl.setOnClickListener {
            SoftKeyboardUtil.hideSoftKeyboard(it)
            val messages: MutableList<String> = java.util.ArrayList()
            messages.add("男")
            messages.add("女")
            val baseTextViewRecyAdapter = BaseTextViewRecyAdapter(ActivityManager.getInstance().currentActivity, messages)
            baseTextViewRecyAdapter.setClickInterface { position: Int ->
                if (position == 0) {
                    gender = "0"
                    gender_tv.text = "男"
                } else if (position == 1) {
                    gender = "1"
                    gender_tv.text = "女"
                }
                PopupWindowUtil.getmInstance().dismissPopWindow()
            }

            PopupWindowUtil.getmInstance().showBottomRecyAndCancelPopupWindow(
                    ActivityManager.getInstance().currentActivity,
                    baseTextViewRecyAdapter,
                    LinearLayoutManager(APPSingleton.getContext(), LinearLayoutManager.VERTICAL, false),
                    R.color.coral)
        }

    }

    var mCountry_id = ""
    var mProvince_id = ""
    var mCity_id = ""
    var mArea_id = ""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && requestCode == BaseConstant.RESULT_CODE.REQUEST_ETRAS && resultCode == Activity.RESULT_OK) {
            if (data.getStringExtra("country_id") != null) mCountry_id = data.getStringExtra("country_id") //国家
            if (data.getStringExtra("province_id") != null) mProvince_id = data.getStringExtra("province_id") //省
            if (data.getStringExtra("city_id") != null) mCity_id = data.getStringExtra("city_id") //市
            if (data.getStringExtra("area_id") != null) mArea_id = data.getStringExtra("area_id") //区choice_end_name
            if (data.getStringExtra("choice_end_name") != null) {
                area_tv.text = data.getStringExtra("choice_end_name")
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMAGE && resultCode == Activity.RESULT_OK) {
            mImageUri = Matisse.obtainResult(data)[0]
            if (mImageUri == null) return
            Glide.with(this).load(mImageUri).into(people_book_first_img)
            mSelectedPictrueUri = mImageUri
            upDateImg(mSelectedPictrueUri!!, 1)
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMAGE2 && resultCode == Activity.RESULT_OK) {
            mImageUri = Matisse.obtainResult(data)[0]
            if (mImageUri == null) return
            Glide.with(this).load(mImageUri).into(people_book_second_img)
            mSelectedPictrueUri2 = mImageUri
            upDateImg(mSelectedPictrueUri2!!, 2)
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMAGE3 && resultCode == Activity.RESULT_OK) {
            mImageUri = Matisse.obtainResult(data)[0]
            if (mImageUri == null) return
            Glide.with(this).load(mImageUri).into(face_card_img)
            mSelectedPictrueUri3 = mImageUri
            upDateImg(mSelectedPictrueUri3!!, 3)
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMAGE4 && resultCode == Activity.RESULT_OK) {
            mImageUri = Matisse.obtainResult(data)[0]
            if (mImageUri == null) return
            Glide.with(this).load(mImageUri).into(face_people_book_first_img)
            mSelectedPictrueUri4 = mImageUri
            upDateImg(mSelectedPictrueUri4!!, 4)
        } else if (requestCode == REQUEST_CAMERA1 && resultCode == Activity.RESULT_OK) {
            if (mSelectedPictrueUri == null) {
                ToastUtils.showToast(mContext, "请重新上传图片")
            } else {
                Glide.with(mContext!!).load(mSelectedPictrueUri).into(people_book_first_img)
                upDateImg(mSelectedPictrueUri!!, 1)
            }
        } else if (requestCode == REQUEST_CAMERA2 && resultCode == Activity.RESULT_OK) {
            if (mSelectedPictrueUri2 == null) {
                ToastUtils.showToast(mContext, "请重新上传图片")
            } else {
                Glide.with(mContext!!).load(mSelectedPictrueUri2).into(people_book_second_img)
                upDateImg(mSelectedPictrueUri2!!, 2)
            }
        } else if (requestCode == REQUEST_CAMERA3 && resultCode == Activity.RESULT_OK) {
            if (mSelectedPictrueUri3 == null) {
                ToastUtils.showToast(mContext, "请重新上传图片")
            } else {
                Glide.with(mContext!!).load(mSelectedPictrueUri3).into(face_card_img)
                upDateImg(mSelectedPictrueUri3!!, 3)

            }
        } else if (requestCode == REQUEST_CAMERA4 && resultCode == Activity.RESULT_OK) {
            if (mSelectedPictrueUri4 == null) {
                ToastUtils.showToast(mContext, "请重新上传图片")
            } else {
                Glide.with(mContext!!).load(mSelectedPictrueUri4).into(face_people_book_first_img)
                upDateImg(mSelectedPictrueUri4!!, 4)

            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    fun upDateImg(mUri: Uri, type: Int) {
        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
        builder.addFormDataPart("key", Mark.State.UserKey)
        val file = FileUtils.getFileByUri(mContext, mUri)
        if (file != null) {
            val imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            builder.addFormDataPart("file", file.name, imageBody)
        }
        mPresenter?.CommitImg(builder.build().parts(), type)

    }

    fun choiceOne(mUri: Int, cameraCode: Int, imageCode: Int) {
        val mUpdatePhotosFragment = UpdatePhotosFragment()
        mUpdatePhotosFragment.mlistener = OnThingClickInterface { msg ->
            if (msg.toString() == "1") {
                when (mUri) {
                    1 -> {
                        mSelectedPictrueUri = ChoiceUtils.takePhoto(this, cameraCode)

                    }
                    2 -> {
                        mSelectedPictrueUri2 = ChoiceUtils.takePhoto(this, cameraCode)

                    }
                    3 -> {
                        mSelectedPictrueUri3 = ChoiceUtils.takePhoto(this, cameraCode)

                    }
                    4 -> {
                        mSelectedPictrueUri4 = ChoiceUtils.takePhoto(this, cameraCode)

                    }

                }

            } else if (msg.toString() == "2") {
                ChoiceUtils.openImg(this, 1, imageCode)


            }
        }
        mUpdatePhotosFragment.show(supportFragmentManager, "UpdatePhotosFragment")
    }

    private fun showPop() {
        val areaCodeAdapter = AreaCodeAdapter(mContext, areaCode)
        areaCodeAdapter.getfixSenderInterface = AreaCodeAdapter.GetfixSenderInterface { postion ->
            LogUtils.d(areaCode.get(postion).getArea_name())
            PopupWindowUtil.getmInstance().dismissPopWindow()
            onChoiceCode(postion)
        }
        PopupWindowUtil.getmInstance().showChoiceAreaPopupwindow(
                mContext,
                area_icon_iv,
                areaCodeAdapter
        )
    }

    private fun onChoiceCode(postion: Int) {
        Glide.with(mContext!!).load(areaCode[postion].icon).into(area_icon_iv)
        area_name_tv.text = areaCode[postion].area_name
        area_code_tv.text = areaCode[postion].code
        codeTag = areaCode[postion].area_code
    }

    override fun initDatas() {
        mPresenter?.getAreaCode(BaseConstant.CURRENTLANGUAGE)
        mPresenter?.onSureTrueNameInformation(Mark.State.UserKey)
    }

    override fun getAreaCodeSuccess(mList: AreaCodeBean) {
        areaCode.addAll(mList.list)
        onChoiceCode(0)

    }

    override fun onCommitSuccess() {
        PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                mContext,
                null,
                "提交成功",
                null
        ) { v: View? ->
            PopupWindowUtil.getmInstance().dismissPopWindow()
            onBackPressed()
        }


    }

    var mImgsList = mutableMapOf<Int, String>()
    override fun onCommitImgSuccess(s: Int, path: String) {
        mImgsList.put(s, path)
    }

    override fun onSureTrueNameInformation(state: String) {
        when (state) {
            "0" -> {
                Glide.with(mContext!!).load(R.drawable.sure_wait_name).into(state_iv)
                state_tv.text = "认证审核中"
                overing_btn.setOnClickListener {
                    onBackPressed()
                }
            }
            "1" -> {
                Glide.with(mContext!!).load(R.drawable.sure_ok_name).into(state_iv)
                state_tv.text = "审核通过"
                overing_btn.setOnClickListener {
                    onBackPressed()
                }
            }
            "-1" -> {
                include_true_name_ll.visibility = View.VISIBLE
                state_cl.visibility = View.GONE
            }
            "2" -> {
                Glide.with(mContext!!).load(R.drawable.sure_ungone_name).into(state_iv)
                state_tv.text = "审核不通过"
                overing_btn.text = "重新提交"
                overing_btn.setOnClickListener {
                    include_true_name_ll.visibility = View.VISIBLE
                    state_cl.visibility = View.GONE
                }
            }
        }
    }

}