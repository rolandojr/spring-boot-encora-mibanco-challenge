package com.example.mibanco.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author sotobotero
 * The effort to standardize rest API error reports  is support by ITEF
 * (Internet Engineering Task Force, open standard organization  that which develop and promotes voluntary internet standards)
 * in RFC 7807 which created a generalized error-handling schema composed by five parts.
 * 1- type — A URI identifier that categorizes the error
 * 2-title — A brief, human-readable message about the error
 * 3-code —  The unique error code
 * 4-detail — A human-readable explanation of the error
 * 5-instance — A URI that identifies the specific occurrence of the error
 * Standarized is optional but have advantage, it is use for facebook and twitter ie
 * https://graph.facebook.com/oauth/access_token?
 * https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 */
@Data
@Builder
public class StandarizedApiExceptionResponse {

    private String type;
    private String title;
    private String code;
    private String detail;
    private String instance;

}
