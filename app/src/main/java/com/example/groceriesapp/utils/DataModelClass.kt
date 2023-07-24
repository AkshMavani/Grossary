package com.example.groceriesapp.utils

import com.squareup.moshi.Json

data class DataModelClass(
                  @Json(name = "image")
                    val img:String?=null,
                  @Json(name = "lname")
                          val lname:String?=null,
                  @Json(name = "rating")
                          val rating:String?=null,
                  @Json(name = "description")
                          val description:String?=null,
                  @Json(name = "price")
                          val price:String?=null,
                  @Json(name = "review")
                          val review:String?=null,
                  @Json(name = "ftitle")
                  val ftitle:String?=null,
                  @Json(name = "kg")
                  val kg:String?=null,
            )
