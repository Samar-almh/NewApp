package api

import com.samar.newapp.AppResponse
import com.samar.newapp.NewItem
import retrofit2.Call
import retrofit2.http.GET

interface NewApi {

    @GET(
        "news_app/api/news_api.php"
    )
    fun fetchNews(): Call<List<NewItem>>
}