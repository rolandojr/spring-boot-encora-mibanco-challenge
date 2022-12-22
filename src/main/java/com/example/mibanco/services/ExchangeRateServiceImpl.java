package com.example.mibanco.services;

import com.example.mibanco.builder.BuilderResponse;
import com.example.mibanco.models.RequestExchangeRate;
import com.example.mibanco.models.ResponseExchangeRate;
import com.example.mibanco.proxy.ExchangeRateRepository;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private BuilderResponse builderResponse;

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate) {

        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", apiKey);

        return exchangeRateRepository.getExchangeRate(
                        requestExchangeRate.getDestinationCurrency(),
                        requestExchangeRate.getOriginCurrency(),
                        requestExchangeRate.getAmount().toString(),
                        headers)
                .doOnError(t -> log.info("Ocurred an Error in ExchangeRateAPI"))
                .onErrorResumeNext(ex -> Single.error(ex))
                .map(rs -> builderResponse.validateResponse(rs))
                .map(exchangeRateLayerAPI -> builderResponse.buildResponse(
                        exchangeRateLayerAPI, requestExchangeRate)
                );
    }
}
