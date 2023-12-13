package com.example.mibanco.models.thirdparty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateConvertResponse {

    private String from;
    private String to;
    private String exchangeRate;
    private String amount;
    private String result;
}
