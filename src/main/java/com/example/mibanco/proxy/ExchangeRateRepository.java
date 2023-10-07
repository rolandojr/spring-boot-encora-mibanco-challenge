package com.example.mibanco.proxy;

import com.example.mibanco.models.thirdparty.ExchangeRateLayerAPI;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

import java.util.Map;


public interface ExchangeRateRepository {

    @GET("/exchangerates_data/convert")
    Single<Response<ExchangeRateLayerAPI>> getExchangeRate(@Query("from") String from,
                                                           @Query("to") String to,
                                                           @Query("amount") String amount,
                                                           @HeaderMap Map<String, String> headers);
}
