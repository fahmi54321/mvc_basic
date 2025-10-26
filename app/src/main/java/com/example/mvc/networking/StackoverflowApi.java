package com.example.mvc.networking;


import com.example.mvc.common.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackoverflowApi {

    @GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&order=desc&sort=activity&site=stackoverflow")
    Call<QuestionsListResponseSchema> fetchLastActiveQuestions(@Query("pagesize") Integer pageSize);

}
