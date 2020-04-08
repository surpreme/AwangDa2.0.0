package com.aite.a.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.jiananshop.a.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;

/**
 * @Auther: valy
 * @datetime: 2020-01-06
 * @desc:
 */
public class ChoiceUtils {
    public static void openImg(Activity activity, int choiceimgNumber, int resultcode) {
        Matisse.from(activity)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
//                .capture(true)//选择照片时，是否显示拍照
//                .captureStrategy(new CaptureStrategy(true, "com.aite.janNanSeller.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .maxSelectable(choiceimgNumber) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new MineGlideEngine()) // 使用的图片加载引擎
                .theme(R.style.Matisse_Dracula)
                .forResult(resultcode); // 设置作为标记的请求码
    }

    public static void openSystemCermar(Activity activity, int result_code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        activity.startActivityForResult(intent, result_code);
    }

    public static Uri takePhoto(Activity activity, int result_code) {
        Uri mImageUri = null;
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开相机的Intent
        if (takePhotoIntent.resolveActivity(activity.getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
            File imageFile = createImageFile(activity);//创建用来保存照片的文件
            if (imageFile != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    /*7.0以上要通过FileProvider将File转化为Uri*/
                    mImageUri = FileProvider.getUriForFile(activity, "com.jiananshop.a.fileProvider", imageFile);
                } else {
                    /*7.0以下则直接使用Uri的fromFile方法将File转化为Uri*/
                    mImageUri = Uri.fromFile(imageFile);
                }
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将用于输出的文件Uri传递给相机
                activity.startActivityForResult(takePhotoIntent, result_code);//打开相机
            }
        }
        return mImageUri;
    }

    /**
     * 创建用来存储图片的文件，以时间来命名就不会产生命名冲突
     *
     * @return 创建的图片文件
     */
    private static File createImageFile(Activity activity) {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File imageFile = null;
//        try {
//            imageFile = File.createTempFile(imageFileName, ".png", storageDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"camerao_photos.png");
        return file;
    }

    public static Uri saveSystemCermarPictrue(Activity activity, int result_code) {
        Uri mImageUri = null;

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开相机的Intent
        if (takePhotoIntent.resolveActivity(activity.getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
            String f = System.currentTimeMillis() + ".jpg";
            File imageFile = new File("/sdcard/pic/" + f);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                /*7.0以上要通过FileProvider将File转化为Uri 包名+.fileprovider*/
                mImageUri = FileProvider.getUriForFile(activity, "com.aite.jiananseller.fileprovider", imageFile);
            } else {
                /*7.0以下则直接使用Uri的fromFile方法将File转化为Uri*/
                mImageUri = Uri.fromFile(imageFile);
            }
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将用于输出的文件Uri传递给相机
            activity.startActivityForResult(takePhotoIntent, result_code);//打开相机
        }
        return mImageUri;


    }


    public static void photoClip(Activity activity, Uri uri, int result_code) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        //这个是处理华为裁剪是圆形框的问题
        if (Build.MANUFACTURER.equals("HUAWEI")) {
            intent.putExtra("aspectX", 9998);
            intent.putExtra("aspectY", 9999);
        } else {
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
        }
        // outputX outputY 是裁剪图片宽高 这个值越大清晰度越高  但是太大了会崩
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, result_code);
    }

//    public static void photoClip(Activity activity, Uri uri, int result_code) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        intent.setDataAndType(uri, "image/*");
//        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
//        intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        // aspectX aspectY 是宽高的比例
//        //这个是处理华为裁剪是圆形框的问题
//        if (Build.MANUFACTURER.equals("HUAWEI")) {
//            intent.putExtra("aspectX", 9998);
//            intent.putExtra("aspectY", 9999);
//        } else {
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//        }
//        // outputX outputY 是裁剪图片宽高  这个值越大清晰度越高  但是太大了会崩
//        intent.putExtra("outputX", 800);
//        intent.putExtra("outputY", 800);
//        intent.putExtra("return-data", false);
//
//        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//        intent.putExtra("noFaceDetection", true);
//        activity.startActivityForResult(intent, CLIP_PHOTO_BY_SELF_REQUEST_CODE);
//    }
}
