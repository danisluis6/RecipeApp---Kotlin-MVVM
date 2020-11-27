package com.enclave.recipeapp.lib

import android.content.Context
import com.enclave.recipeapp.frameworks.utilities.Constant
import com.enclave.recipeapp.localstorage.entities.RecipeType
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.util.ArrayList

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
class XmlParserUtils(val context: Context) {

    /*
     * @param resIds
     * @return a XmlPullParser that can be used to convert file recipetypes.xml into XmlPullParser.
     */
    fun getXmlPullParser(resIds: Int): XmlPullParser? {
        try {
            val parserFactory = XmlPullParserFactory.newInstance()
            val parser = parserFactory.newPullParser()
            val inputStream = context.resources.openRawResource(resIds)
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            return parser
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }

        return null
    }

    /*
     * @param xmlPullParser
     * @return List<RecipeType> from file recipetypes.xml
     */
    fun processParsing(xmlPullParser: XmlPullParser): ArrayList<RecipeType> {
        val recipeTypes = ArrayList<RecipeType>()
        var eventType = 0
        try {
            eventType = xmlPullParser.eventType
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }

        var currentRecipeType: RecipeType? = null
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val eltName: String
            if (eventType == XmlPullParser.START_TAG) {
                eltName = xmlPullParser.name
                if (Constant.Recipe == eltName) {
                    currentRecipeType = RecipeType(Integer.parseInt(xmlPullParser.getAttributeValue(0)), "")
                    recipeTypes.add(currentRecipeType)
                }
                if (recipeTypes.size != 0 && Constant.Name == eltName) {
                    try {
                        recipeTypes[recipeTypes.size - 1].name = xmlPullParser.nextText()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: XmlPullParserException) {
                        e.printStackTrace()
                    }
                }
            }
            try {
                eventType = xmlPullParser.next()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: XmlPullParserException) {
                e.printStackTrace()
            }

        }
        return recipeTypes
    }

}