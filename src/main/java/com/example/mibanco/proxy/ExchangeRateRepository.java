package com.example.mibanco.proxy;

import com.example.mibanco.models.thirdparty.ExchangeRateConvertResponse;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRateRepository {


    @GET("/trade-banking/currency/v1/exchange/convert")
    Single<Response<ExchangeRateConvertResponse>> getExchangeRate(@Query("from") String from,
                                                                  @Query("to") String to,
                                                                  @Query("amount") String amount);
}