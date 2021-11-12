package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.network.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
//Todo 4: Remove the key from the interface parameter
    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategories(@Query("category") category:String):Call<TopNewsResponse>
}