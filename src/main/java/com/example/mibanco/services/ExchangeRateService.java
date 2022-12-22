package com.example.mibanco.services;

import com.example.mibanco.models.RequestExchangeRate;
import com.example.mibanco.models.ResponseExchangeRate;
import io.reactivex.Single;

public interface ExchangeRateService {

    Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate);
}
