package eu.tutorials.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import eu.tutorials.newsapp.network.models.TopNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Todo 11: create the NewsManager class
class NewsManager {

    //Todo 12: create setter and composable getter variable to hold the response
    private val _newsResponse =
        mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }


    //Todo 14: call the method in the init block so it gets triggered once the class is initialized
    init {
        getArticles()
    }

    /**Todo 13: create a method and process the request
     * In getTopArticles we pass in the country and apiKey parameter
     * we use enqueue method to asynchronously process the request
     * if the response is successful we hold the value else we log the error
     * if there is a failure we log the trace to see what can be the cause
     */
    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles("us","")
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("news","${_newsResponse.value}")
                }else{
                    Log.d("error","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.printStackTrace()}")
            }

        })
    }
}