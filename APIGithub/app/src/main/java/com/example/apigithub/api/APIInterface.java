package com.example.apigithub.api;

import com.example.apigithub.model.ItemModel;
import com.example.apigithub.model.RepositoryModel;
import com.example.apigithub.model.ResponseModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface APIInterface {
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("repositories")
    Call<ResponseModel> getRepos(@QueryMap Map<String, String> params);

}
