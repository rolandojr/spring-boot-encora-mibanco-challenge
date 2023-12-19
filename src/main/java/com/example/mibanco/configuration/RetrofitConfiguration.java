package com.example.mibanco.configuration;

import com.example.mibanco.proxy.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@AllArgsConstructor
public class RetrofitConfiguration {

    private ApplicationProperties applicationProperties;

    @Bean
    public ExchangeRateRepository configuration() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(applicationProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(applicationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(applicationProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(applicationProperties.getClientUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        ExchangeRateRepository exchangeRateRepository = retrofit.create(ExchangeRateRepository.class);
        return exchangeRateRepository;
    }


}
