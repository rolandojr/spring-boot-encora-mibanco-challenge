package com.example.mibanco.models.thirdparty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseExchangeRate {
    private String amount;
    private String amountExchangeRate;
    private String originCurrency;
    private String destinationCurrency;
    private String exchangeRate;
}
