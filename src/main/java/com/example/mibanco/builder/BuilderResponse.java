package com.example.mibanco.builder;

import com.example.mibanco.models.thirdparty.ExchangeRateLayerAPI;
import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.exception.ApiException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import retrofit2.Response;

@Component
@Slf4j
@NoArgsConstructor
public class BuilderResponse {

    public ExchangeRateLayerAPI validateResponse(Response<ExchangeRateLayerAPI> response) throws Exception {
        if (!response.isSuccessful())  {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            JSONObject error = jObjError.getJSONObject("error");
            String code = error.getString("code");
            String message = error.getString("message");
            throw ApiException.builder().httpStatus(HttpStatus.CONFLICT).code("E0001").message(message).build();
        }
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
