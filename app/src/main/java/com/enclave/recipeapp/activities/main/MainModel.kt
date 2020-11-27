package com.enclave.recipeapp.activities.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.enclave.recipeapp.R

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

class MainModel : ViewModel() {

    fun handleView(context: Context, activity: MainActivity) {
        Log.i("TAG", activity.getString(R.string.app_name))
    }

}