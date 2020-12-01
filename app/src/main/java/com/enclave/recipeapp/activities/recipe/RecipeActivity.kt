package com.enclave.recipeapp.activities.recipe

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.enclave.recipeapp.R
import com.enclave.recipeapp.activities.BaseActivity
import com.enclave.recipeapp.databinding.ActivityRecipeBinding
import com.enclave.recipeapp.frameworks.adapter.TypeRecicpeAdapter
import com.enclave.recipeapp.frameworks.utilities.Constant
import com.enclave.recipeapp.frameworks.utilities.Helper
import com.enclave.recipeapp.lib.XmlParserUtils
import com.google.android.material.snackbar.Snackbar
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.xmlpull.v1.XmlPullParser

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
 * | forbidden.
 * | This computer program is protected by copyright law.
 * | Unauthorized reproduction or distribution of this program, or any portion of
 * | if, may result in severe civil and criminal penalties and will be prosecuted
 * | to the maximum extent possible under the law.
 * +--------------------------------------------------------------------------
 */

class RecipeActivity : BaseActivity(), KoinComponent {

    private val context: Context by inject()
    private val xmlParserUtils: XmlParserUtils by inject()
    private val typeRecicpeAdapter : TypeRecicpeAdapter by inject()


    private lateinit var binding: ActivityRecipeBinding
    private lateinit var viewModel: RecipeModel

    @TargetApi(Build.VERSION_CODES.M)
    override fun bindingView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe)
        binding.contentView.spinnerRecipeTypes.apply {
            adapter = typeRecicpeAdapter
        }
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(RecipeModel::class.java)
        binding.viewModel = viewModel

        binding.toolbar.title = getString(R.string.activity_recipe)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.black))
    }

//    private fun generateSmartHomeConfiguredGroupieItems(recipeTypes : ArrayList<RecipeType>): MutableCollection<out Group> {
//        val mutableList = mutableListOf<RecipeTypeViewHolder>()
//        for (element in recipeTypes) {
//            mutableList.add(RecipeTypeViewHolder(element, this))
//        }
//        return mutableList
//    }

    override fun initAttributes() {
        viewModel.handleView(this)

        val xmlPullParser = xmlParserUtils.getXmlPullParser(R.raw.recipetypes) as XmlPullParser
        val recipeTypes = xmlParserUtils.processParsing(xmlPullParser)
        typeRecicpeAdapter.sync(recipeTypes)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constant.GALLERY) {
            if (data == null) {
                return
            }
            val uri = data.data
            try {
                var path = uri?.let { Helper.getRealPathFromURI(it, this) }
                if (path == null) {
                    path = uri?.path
                }
                path?.let {
                    Helper.fixOrientationBugOfProcessedBitmap(
                        context,
                        BitmapFactory.decodeFile(path), it
                    )
                }
                updateImageRecipe(path)
            } catch (e: Exception) {
                showSnackBar(context.getString(R.string.error_pick_image_from_gallery))
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateImageRecipe(path: String?) {
        Glide.with(context)
            .load(path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.labelPicture.set(true)
                    viewModel.imgPicture.set(false)
                    showSnackBar(context.getString(R.string.error_image_load))
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.labelPicture.set(false)
                    viewModel.imgPicture.set(true)
                    return true
                }
            })
            .into(binding.contentView.imgPicture)
    }

    private fun showSnackBar(title: String) {
        val snackbar = Snackbar.make(
            window.decorView.findViewById(android.R.id.content),
            title,
            Snackbar.LENGTH_LONG
        )
            .setAction(null, null)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        snackbar.show()
    }

    override fun onBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        super.onBackPressed()
    }
}