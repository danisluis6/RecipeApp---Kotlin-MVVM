package com.enclave.recipeapp.frameworks.utilities

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import java.io.File
import java.util.*

/*
 *
 * +--------------------------------------------------------------------------
 * |
 * | WARNING: REMOVING THIS COPYRIGHT HEADER IS EXPRESSLY FORBIDDEN
 * |
 * | THE GOOD LIFE
 * | ========================================
 * | by ENCLAVE, INC.
 * | (c) 2012-2013 ENCLAVEIT.COM - All right reserved
 * | Website: http://www.enclaveit.com [^]
 * | Email : engineering@enclave.vn
 * | ========================================
 * |
 * | WARNING //--------------------------
 * |
 * | Selling the code for this program without prior written consent is expressly
 * |forbidden.
 * | This computer program is protected by copyright law.
 * | Unauthorized reproduction or distribution of this program, or any portion of
 * | if, may result in severe civil and criminal penalties and will be prosecuted
 * |to the maximum extent possible under the law.
 * +--------------------------------------------------------------------------
 */

class Helper {

    /*
     * This method can be used to check READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE will be granted by Android device.
     * @param activity
     * @return
     */
    companion object {

        fun checkPermissions(activity: Activity): Boolean {
            return ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }

        /* Request permissions and allow user to accept it.
         * @param activity
         */
        @TargetApi(Build.VERSION_CODES.M)
        fun establishPermission(activity: Activity) {
            activity.requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), Constant.PERMISSION
            )
        }

        /*
         * This method can be used to get root path from Android device.
         * Using Cursor to query.
         * @param contentUri
         * @param activity
         * @return
         */
        fun getRealPathFromURI(contentUri: Uri, activity: Activity): String? {
            var pathFromURI: String? = null
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = activity.contentResolver.query(contentUri, proj, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    pathFromURI = cursor.getString(column_index)
                }
                cursor.close()
            }
            return pathFromURI
        }

        /*
         * This method can be used to fix  error when image rotates.
         * @param context
         * @param bitmap
         * @param mImagePath
         * @return
         */
        fun fixOrientationBugOfProcessedBitmap(
            context: Context,
            bitmap: Bitmap,
            mImagePath: String
        ): Bitmap? {
            try {
                if (getCameraPhotoOrientation(context, Uri.parse(mImagePath)) == 0) {
                    return bitmap
                } else {
                    val matrix = Matrix()
                    matrix.postRotate(
                        getCameraPhotoOrientation(
                            context,
                            Uri.fromFile(File(mImagePath))
                        ).toFloat()
                    )
                    // Recreate the new Bitmap and set it back
                    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }

        }

        /*
         * Detect direction when rotate.
         * @param context
         * @param imageUri
         * @return
         */
        private fun getCameraPhotoOrientation(context: Context, imageUri: Uri): Int {
            var rotate = 0
            try {
                context.contentResolver.notifyChange(imageUri, null)
                val exif = ExifInterface(
                    Objects.requireNonNull(imageUri.path)
                )
                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return rotate
        }


        /*
         * This method can be used to hidden Keyboard
         * @param activity
         */
        fun hiddenKeyBoard(activity: Activity) {
            val view = activity.currentFocus
            if (view != null) {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

    }

}