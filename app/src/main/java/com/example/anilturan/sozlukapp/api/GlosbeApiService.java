package com.example.anilturan.sozlukapp.api;

import com.example.anilturan.sozlukapp.model.GlosbeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GlosbeApiService {
    @GET("translate")
    Call<GlosbeResponse> getApiMean2(@Query("from") String from, @Query("dest") String dest, @Query("format") String format, @Query("phrase") String phrase);
    }

