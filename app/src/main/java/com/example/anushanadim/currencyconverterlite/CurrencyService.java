package com.example.anushanadim.currencyconverterlite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CurrencyService {

    @GET("/exchange")
    Call<String> Coin(@Header("X-Mashape-Key") String header, @Query("from") String from, @Query("q") String value, @Query("to") String to);

}
