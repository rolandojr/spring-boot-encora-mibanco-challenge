package com.example.mibanco.models.thirdparty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestExchangeRate {
    private String amount;
    private String originCurrency;
    private String destinationCurrency;
}
