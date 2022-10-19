package com.example.shutter.data.entity

import com.google.gson.annotations.SerializedName

data class Pics(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val search_id: String,
    val spellcheck_info: SpellcheckInfo,
    val total_count: Int
)