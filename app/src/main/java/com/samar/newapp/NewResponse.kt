package com.samar.newapp

import com.google.gson.annotations.SerializedName

class NewResponse {
    @SerializedName("new")
    lateinit var newItems: List<NewItem>
}