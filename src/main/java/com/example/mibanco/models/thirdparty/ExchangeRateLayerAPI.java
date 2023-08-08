package com.example.mibanco.models.thirdparty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRateLayerAPI {

    private Boolean success;
    private ExchangeRateLayerQuery query;
    private ExchangeRateLayerInfo info;
    private String date;
    private Double result;

}
