package com.example.shutter.data.entity

import com.google.gson.annotations.SerializedName

data class Data(
   // @SerializedName("aspect")
    val aspect: Double,
  //  @SerializedName("assets")
    val assets: Assets,
    //val contributor: Contributor,
    //@SerializedName("description")
    val description: String,
   // val has_model_release: Boolean,
   // val id: String,
   // val image_type: String,
   // val media_type: String
)