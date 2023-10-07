package com.example.mibanco.models.thirdparty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRateLayerQuery {
    private String from;
    private String to;
    private Double amount;
}
