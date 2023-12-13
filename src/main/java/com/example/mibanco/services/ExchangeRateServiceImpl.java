package com.example.mibanco.services;

import com.example.mibanco.builder.BuilderResponse;
import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.proxy.ExchangeRateRepository;
import com.example.mibanco.util.exception.ExceptionBuilder;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.operator.BulkheadOperator;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.operator.RateLimiterOperator;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.mibanco.util.constants.Constants.COMPONENT_EXCHANGE;

@Slf4j
@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private ExceptionBuilder exceptionBuilder;
    private ExchangeRateRepository exchangeRateRepository;
    private BuilderResponse builderResponse;
    private CircuitBreaker circuitBreaker;
    private RateLimiter rateLimiter;
    private Bulkhead bulkhead;


    @Override
    public Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate) {
        return exchangeRateRepository.getExchangeRate(
                        requestExchangeRate.getOriginCurrency(), requestExchangeRate.getDestinationCurrency(),
                        requestExchangeRate.getAmount())
                .compose(CircuitBreakerOperator.of(circuitBreaker))
                .compose(RateLimiterOperator.of(rateLimiter))
                .compose(BulkheadOperator.of(bulkhead))
                .doOnError(t -> log.info("Ocurred an Error in ExchangeRateAPI {}", t))
                .onErrorResumeNext(ex -> Single.error(exceptionBuilder.buildExternalException(ex, COMPONENT_EXCHANGE)))
                .map(rs -> builderResponse.validateResponse(rs))
                .map(exchangeRateConvertResponse -> builderResponse.buildResponse(
                        exchangeRateConvertResponse, requestExchangeRate)
                )
                .subscribeOn(Schedulers.io());
    }

}
