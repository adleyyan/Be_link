package com.hq.bms.common.exception;

import lombok.Data;

/**
 * @author Adley.yan
 */
@Data
public  class MyException extends Exception {
    private String messgae;
    private String code;

    public MyException(String messgae){
        this.messgae = messgae;
    }


    @Override
    public String toString() {
        return "MyException{" +
                "messgae='" + this.getMessgae() + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
