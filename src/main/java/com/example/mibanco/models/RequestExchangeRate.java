package com.example.mibanco.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestExchangeRate {
    private Double amount;
    private String originCurrency;
    private String destinationCurrency;
}
