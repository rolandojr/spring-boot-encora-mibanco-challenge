package com.example.mibanco.builder;

import com.example.mibanco.models.ExchangeRateLayerAPI;
import com.example.mibanco.models.RequestExchangeRate;
import com.example.mibanco.models.ResponseExchangeRate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Response;

@Component
@Slf4j
@NoArgsConstructor
public class BuilderResponse {

    public ExchangeRateLayerAPI validateResponse(Response<ExchangeRateLayerAPI> response) {
        return response.body();
    }

    public ResponseExchangeRate buildResponse(ExchangeRateLayerAPI exchangeRateLayerAPI, RequestExchangeRate requestExchangeRate) {
        return ResponseExchangeRate.builder()
                .amount(requestExchangeRate.getAmount())
                .amountExchangeRate(exchangeRateLayerAPI.getResult())
                .originCurrency(requestExchangeRate.getOriginCurrency())
                .destinationCurrency(requestExchangeRate.getDestinationCurrency())
                .exchangeRate(exchangeRateLayerAPI.getInfo().getRate())
                .build();
    }
}
