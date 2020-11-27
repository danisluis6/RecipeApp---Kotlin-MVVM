package com.enclave.recipeapp.frameworks.viewholder

import com.enclave.recipeapp.R
import com.enclave.recipeapp.databinding.RecipeTypeViewBinding
import com.enclave.recipeapp.localstorage.entities.RecipeType
import com.xwray.groupie.databinding.BindableItem

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
class RecipeTypeViewHolder(private val recipeType: RecipeType,
                           private val triggerRecipeTypeListener: OnTriggerRecipeTypeEventListener) : BindableItem<RecipeTypeViewBinding>() {

    override fun getLayout(): Int = R.layout.recipe_type_view

    override fun bind(viewBinding: RecipeTypeViewBinding, position: Int) {
        updateItemView(viewBinding, position)
    }

    private fun updateItemView(viewBinding: RecipeTypeViewBinding, position: Int) {
        viewBinding.recipeTypeName.text = recipeType.name
    }

    interface OnTriggerRecipeTypeEventListener {
        fun onMore(position: Int, recipeType: RecipeType)
        fun onPlay(position: Int, recipeType: RecipeType)
    }

}