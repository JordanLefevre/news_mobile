package com.example.jordanlefevre.helloworld;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jordanlefevre on 17/03/2017.
 */

public interface CategoriesAPI {
    @GET("?json=get_category_index")
    Call<ResponseCategories> getCategories();
}
