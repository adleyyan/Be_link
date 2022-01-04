package com.hq.bms.common.exception;

import lombok.Data;

/**
 * @author Adley.yan
 */
@Data
public class DefinitionException extends RuntimeException{
    private Integer errorCode;
    private String errorMsg;

    public DefinitionException(){

    }
    public DefinitionException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
