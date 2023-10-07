package com.example.mibanco.controllers;

import com.example.mibanco.models.thirdparty.RequestExchangeRate;
import com.example.mibanco.models.thirdparty.ResponseExchangeRate;
import com.example.mibanco.services.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
@AllArgsConstructor
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<ResponseExchangeRate>> exchangeRate(@RequestBody RequestExchangeRate requestExchangeRate) {
        return exchangeRateService.exchangeRate(requestExchangeRate)
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.io());
    }


}
