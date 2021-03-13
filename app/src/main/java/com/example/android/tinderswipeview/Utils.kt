package com.example.android.tinderswipeview

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


/**
 * Created by Sadate Tchamouza on 3/13/21.
 */


object Utils {
    private const val TAG = "Utils"
    fun loadProfiles(context: Context): List<Profile>? {
        return try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val array = JSONArray(loadJSONFromAsset(context, "profiles.json"))
            val profileList: MutableList<Profile> =
                ArrayList()
            for (i in 0 until array.length()) {
                val profile =
                    gson.fromJson(
                        array.getString(i),
                        Profile::class.java
                    )
                profileList.add(profile)
            }
            profileList
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
        var json: String? = null
        var `is`: InputStream? = null
        try {
            val manager: AssetManager = context.getAssets()
            Log.d(TAG, "path $jsonFileName")
            `is` = manager.open(jsonFileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getDisplaySize(windowManager: WindowManager): Point {
        return try {
            if (Build.VERSION.SDK_INT > 16) {
                val display = windowManager.defaultDisplay
                val displayMetrics = DisplayMetrics()
                display.getMetrics(displayMetrics)
                Point(displayMetrics.widthPixels, displayMetrics.heightPixels)
            } else {
                Point(0, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Point(0, 0)
        }
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density) as Int
    }
}