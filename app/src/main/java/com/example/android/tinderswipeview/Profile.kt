package com.example.android.tinderswipeview

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Sadate Tchamouza on 3/13/21.
 */


data class Profile (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("url")
    @Expose
    var imageUrl: String? = null,

    @SerializedName("age")
    @Expose
    var age: Int? = null,

    @SerializedName("location")
    @Expose
    var location: String? = null)