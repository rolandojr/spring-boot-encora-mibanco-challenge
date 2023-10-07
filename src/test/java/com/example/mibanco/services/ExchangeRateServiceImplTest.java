package com.example.mibanco.services;

import com.example.mibanco.builder.BuilderResponse;
import com.example.mibanco.configuration.ApplicationProperties;
import com.example.mibanco.models.thirdparty.ExchangeRateLayerAPI;
import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.proxy.ExchangeRateRepository;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {


    @Mock
    private ExchangeRateRepository exchangeRateRepository;
    @Mock
    private BuilderResponse builderResponse;
    @Mock
    private ApplicationProperties properties;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    void validateExchangeRate() throws Exception {

        RequestExchangeRate requestExchangeRate =  RequestExchangeRate.builder()
                .originCurrency("PEN")
                .destinationCurrency("USD")
                .amount(Double.valueOf("100"))
                .build();

        when(properties.getApiKey()).thenReturn("API_KEY");
        when(exchangeRateRepository.getExchangeRate(anyString(), anyString(), anyString(), anyMap()))
                .thenReturn(Single.just(Response.success(ExchangeRateLayerAPI.builder().build())));
        when(builderResponse.validateResponse(any(Response.class)))
                .thenReturn(ExchangeRateLayerAPI.builder().build());
        when(builderResponse.buildResponse(any(ExchangeRateLayerAPI.class), any(RequestExchangeRate.class)))
                .thenReturn(ResponseExchangeRate.builder().build());

        TestObserver<ResponseExchangeRate> testObserver =  exchangeRateService.exchangeRate(requestExchangeRate).test();

        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();


    }
}