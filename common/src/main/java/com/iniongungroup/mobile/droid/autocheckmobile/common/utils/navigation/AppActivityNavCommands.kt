package com.iniongungroup.mobile.droid.autocheckmobile.common.utils.navigation

import android.content.Context
import android.content.Intent
import com.iniongungroup.mobile.droid.autocheckmobile.common.BuildConfig

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

object AppActivityNavCommands {

    fun getInventoryActivityIntent(context: Context) = navigationIntent(context, BuildConfig.INVENTORY_NAV_INTENT)

    private fun navigationIntent(context: Context, navAction: String) = Intent(navAction).setPackage(context.packageName)

}