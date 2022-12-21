package com.example.mibanco.services;

import com.example.mibanco.models.ExchangeRateLayerAPI;
import com.example.mibanco.models.RequestExchangeRate;
import com.example.mibanco.models.ResponseExchangeRate;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

public interface ExchangeRateService {

    Single<ResponseExchangeRate> exchangeRate(RequestExchangeRate requestExchangeRate);
}
