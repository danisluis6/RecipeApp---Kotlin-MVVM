package com.enclave.recipeapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        attachViewModel()
        initAttributes()
    }

    protected abstract fun bindingView()

    protected abstract fun attachViewModel()

    protected abstract fun initAttributes()

}