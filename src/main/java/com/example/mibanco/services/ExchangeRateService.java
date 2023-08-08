package com.example.mibanco.services;

import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import io.reactivex.Single;

public interface ExchangeRateService {

    Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate);
}
