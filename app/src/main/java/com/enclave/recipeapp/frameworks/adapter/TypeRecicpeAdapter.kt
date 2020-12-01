package com.enclave.recipeapp.frameworks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.enclave.recipeapp.R
import com.enclave.recipeapp.localstorage.entities.RecipeType


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

class TypeRecicpeAdapter(val context: Context, private var recipeTypes: List<RecipeType>) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: SpinnerRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.recipe_type_view, parent, false)
            vh = SpinnerRowHolder(view)
            view!!.tag = vh
        } else {
            view = convertView
            vh = view.tag as SpinnerRowHolder
        }

        vh.label.text = recipeTypes[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return recipeTypes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recipeTypes.size
    }

    fun sync(recipeTypes: ArrayList<RecipeType>) {
        this.recipeTypes = recipeTypes
        notifyDataSetChanged()
    }

    private class SpinnerRowHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.recipeTypeName) as TextView
    }

}