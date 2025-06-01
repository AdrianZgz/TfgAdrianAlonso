package com.example.soyaragon.Domain

import java.io.Serializable

data class CampaignItemModel(
    var Title: String="",
    var Description: String="",
    var Poster: String="",
    var Month: String="",
    var Trailer: String="",
    var Shops: ArrayList<ShopModel> =ArrayList()
):Serializable
