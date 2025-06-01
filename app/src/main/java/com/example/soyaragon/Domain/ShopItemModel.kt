package com.example.soyaragon.Domain

import java.io.Serializable

data class ShopItemModel(
    var Title: String="",
    var Description: String="",
    var Poster: String="",
    var Time: String="",
    var Trailer: String="",
    var Imdb: Int=0,
    var Year: Int=0,
    var Price: String="",
    var Genre: ArrayList<String> =ArrayList(),
    var Casts: ArrayList<CastModel> =ArrayList()
):Serializable
