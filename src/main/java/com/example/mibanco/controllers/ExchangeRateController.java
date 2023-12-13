package com.example.mibanco.controllers;

import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.services.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/exchange")
@AllArgsConstructor
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @PostMapping
    public Single<ResponseEntity<ResponseExchangeRate>> exchangeRate(@RequestBody RequestExchangeRate requestExchangeRate) {
        return exchangeRateService.exchangeRate(requestExchangeRate)
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.io());
    }


}
