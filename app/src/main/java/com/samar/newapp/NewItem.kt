package com.samar.newapp

import com.google.gson.annotations.SerializedName

data class NewItem(
              var news_title: String = "",
              var news_details: String="",
              var id: String = ""
)
