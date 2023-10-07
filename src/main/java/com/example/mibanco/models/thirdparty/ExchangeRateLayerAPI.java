package com.example.mibanco.models.thirdparty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateLayerAPI {

    private Boolean success;
    private ExchangeRateLayerQuery query;
    private ExchangeRateLayerInfo info;
    private String date;
    private Double result;

}
