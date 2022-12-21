package com.example.mibanco.controllers;

import com.example.mibanco.models.ExchangeRateLayerAPI;
import com.example.mibanco.models.RequestExchangeRate;
import com.example.mibanco.models.ResponseExchangeRate;
import com.example.mibanco.services.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<ResponseExchangeRate>> exchangeRate(@RequestBody RequestExchangeRate requestExchangeRate) {
        return exchangeRateService.exchangeRate(requestExchangeRate)
                .subscribeOn(Schedulers.io())
                .map(responseExchangeRate -> ResponseEntity.ok(responseExchangeRate));

    }

}
