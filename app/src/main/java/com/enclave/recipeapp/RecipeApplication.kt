package com.enclave.recipeapp

import android.app.Application
import com.vogo.geographyintellect.frameworks.di.module.adapterModule
import com.vogo.geographyintellect.frameworks.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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

class RecipeApplication : Application() {

    override fun onCreate() {
        startKoin{
            androidContext(this@RecipeApplication)
            modules(appModule, adapterModule)
        }
        super.onCreate()
    }

}