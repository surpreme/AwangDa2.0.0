package com.valy.baselibrary.file

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.valy.baselibrary.utils.LogUtils
import org.jetbrains.annotations.NotNull
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**

 * @Auther: valy

 * @datetime: 2020/3/24

 * @desc:
 * todo

 */
object GlideBuilder {
    fun getBitmap(@NotNull activity: Activity, @NotNull url: String, bitmap: Bitmap?) {
        Thread(Runnable {
            try {
                val bitmap = Glide.with(activity)
                        .asBitmap()
                        .load(url)
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()
                val photo: File = Glide.with(activity)
                        .load(url)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
    fun getImageContentUri(@NotNull context: Context, imageFile: File): Uri? {
        val filePath = imageFile.absolutePath
        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media._ID), MediaStore.Images.Media.DATA + "=? ", arrayOf(filePath), null)
        return if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
            val baseUri = Uri.parse("content://media/external/images/media")
            Uri.withAppendedPath(baseUri, "" + id)
        } else {
            if (imageFile.exists()) {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.DATA, filePath)
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            } else {
                null
            }
        }
    }
    /**
     * 保存方法
     */
    fun saveBitmap(bitmap: Bitmap) {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/jnIns.png")
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.flush()
            out.close()
            LogUtils.d("已经保存")
        } catch (e: FileNotFoundException) { // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }
}