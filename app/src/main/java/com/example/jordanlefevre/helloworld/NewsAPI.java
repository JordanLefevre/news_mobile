package com.example.jordanlefevre.helloworld;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jordanlefevre on 16/03/2017.
 */

public interface NewsAPI {
    @GET("?json=get_recent_posts")
    Call<ResponseNews> getRecentNews();

    @GET("?json=get_category_posts")
    Call<ResponseNews> getNewsByCategory(@Query("id") int category_id);
}
