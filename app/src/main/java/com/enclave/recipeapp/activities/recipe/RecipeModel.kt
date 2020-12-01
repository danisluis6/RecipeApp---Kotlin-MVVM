package com.enclave.recipeapp.activities.recipe

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.enclave.recipeapp.frameworks.utilities.Constant
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
 *
 * +--------------------------------------------------------------------------
 * |
 * | WARNING: REMOVING THIS COPYRIGHT HEADER IS EXPRESSLY FORBIDDEN
 * |
 * | RECIPE APPLICATION
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

class RecipeModel : ViewModel(), KoinComponent {

    val labelPicture = ObservableBoolean(false)
    val imgPicture = ObservableBoolean(false)

    private val context: Context by inject()
    private lateinit var mActivity: RecipeActivity

    fun handleView(activity: RecipeActivity) {
        mActivity = activity
    }

    fun onUploadImage() {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mActivity.startActivityForResult(pickPhoto, Constant.GALLERY)
    }

}