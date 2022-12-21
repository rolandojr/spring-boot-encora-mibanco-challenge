package com.example.mibanco.configuration;

import com.example.mibanco.proxy.ExchangeRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    private ExchangeRateRepository exchangeRateRepository;

    @Bean
    public ExchangeRateRepository configuration(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apilayer.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        exchangeRateRepository = retrofit.create(ExchangeRateRepository.class);
        return exchangeRateRepository;
    }
}
