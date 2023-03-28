package com.bldevelopers.ankur.network

import com.bldevelopers.ankur.models.Cat_Detail_Model
import com.bldevelopers.ankur.models.categories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {

    @GET("/categories.json")
    suspend fun getCategories(): Response<List<categories>>

    @GET("{category_detail_url}")
    suspend fun getCatDetails(
        @Path("category_detail_url") CatDetUrl: String
    ): Response<List<Cat_Detail_Model>>


}