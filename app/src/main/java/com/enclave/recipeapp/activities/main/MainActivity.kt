package com.enclave.recipeapp.activities.main

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.enclave.recipeapp.R
import com.enclave.recipeapp.activities.BaseActivity
import com.enclave.recipeapp.activities.recipe.RecipeActivity
import com.enclave.recipeapp.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*
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

class MainActivity : BaseActivity(), KoinComponent {

    val context: Context by inject()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainModel

    @TargetApi(Build.VERSION_CODES.M)
    override fun bindingView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(MainModel::class.java)
        binding.viewModel = viewModel

        binding.toolbar.title = context.getString(R.string.activity_main)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.black))
    }

    override fun initAttributes() {
        viewModel.handleView(context,this)

        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
