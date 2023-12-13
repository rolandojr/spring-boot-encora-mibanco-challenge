package com.example.mibanco.builder;

import com.example.mibanco.exception.ApiException;
import com.example.mibanco.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
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

    public ExchangeRateConvertResponse validateResponse(Response<ExchangeRateConvertResponse> response) throws Exception {
        if (!response.isSuccessful()) {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            JSONObject error = jObjError.getJSONObject("error");
            String code = error.getString("code");
            String message = error.getString("message");
            throw ApiException.builder()
                    .httpStatus(HttpStatus.CONFLICT)
                    .code(code)
                    .message(message)
                    .build();
        }
        return response.body();
    }

    public ResponseExchangeRate buildResponse(ExchangeRateConvertResponse exchangeRateConvertResponse, RequestExchangeRate requestExchangeRate) {

        return ResponseExchangeRate.builder()
                .amount(requestExchangeRate.getAmount())
                .amountExchangeRate(exchangeRateConvertResponse.getResult())
                .originCurrency(requestExchangeRate.getOriginCurrency())
                .destinationCurrency(requestExchangeRate.getDestinationCurrency())
                .exchangeRate(exchangeRateConvertResponse.getExchangeRate())
                .build();
    }
}
