package com.example.mibanco.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseExchangeRate {
    private Double amount;
    private Double amountExchangeRate;
    private String originCurrency;
    private String destinationCurrency;
    private String exchangeRate;
}
