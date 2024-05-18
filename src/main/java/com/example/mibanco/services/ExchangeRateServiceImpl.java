package com.example.mibanco.services;

import com.example.mibanco.builder.BuilderResponse;
import com.example.mibanco.exception.ApiException;
import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.proxy.ExchangeRateRepository;
import com.example.mibanco.util.exception.ExceptionBuilder;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.mibanco.util.constants.Constants.COMPONENT_EXCHANGE;

@Slf4j
@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private ExceptionBuilder exceptionBuilder;
    private ExchangeRateRepository exchangeRateRepository;
    private BuilderResponse builderResponse;

    @Override
    @CircuitBreaker(name = "exchange", fallbackMethod = "fallbackMethodExecute")
    public Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate) {
        return exchangeRateRepository.getExchangeRate(
                        requestExchangeRate.getOriginCurrency(), requestExchangeRate.getDestinationCurrency(),
                        requestExchangeRate.getAmount())
                .doOnError(t -> log.info("Ocurred an Error in ExchangeRateAPI {}",t.getMessage()))
                .onErrorResumeNext(ex -> Single.error(exceptionBuilder.buildExternalException(ex, COMPONENT_EXCHANGE)))
                .map(rs -> builderResponse.validateResponse(rs))
                .map(exchangeRateConvertResponse -> builderResponse.buildResponse(
                        exchangeRateConvertResponse, requestExchangeRate)
                )
                .subscribeOn(Schedulers.io());
    }

    public Single<ResponseExchangeRate> fallbackMethodExecute(RequestExchangeRate requestExchangeRate, CallNotPermittedException exception) {
        log.info("executing fallback method !!");
        return Single.error(
                exceptionBuilder.buildExternalException(exception, COMPONENT_EXCHANGE));
    }

}
