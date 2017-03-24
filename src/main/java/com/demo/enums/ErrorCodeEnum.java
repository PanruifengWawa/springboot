package com.demo.enums;

import java.io.Serializable;

/**
 * Created by TIANCHENGYUAN103 on 2015-12-04.
 */
public enum ErrorCodeEnum implements Serializable {

    No_Error("No ERROE!", 0),
    Error("Unknown error!", 1),
    Null_Input_Error("Null Input Error",2),
    Email_Format_Error("Email Formart Error",3),
    User_UnPassed("User UnPassed",4),
    User_To_Be_Passed("User To Be Passed",5)
    ;

    private String label;
    private Integer code;

    ErrorCodeEnum() {
    }

    ErrorCodeEnum(String label, Integer code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code.toString();
    }

    public static ErrorCodeEnum parse(int code) {
        for (ErrorCodeEnum theEnum : ErrorCodeEnum.values()) {
            if (theEnum.getCode() == code) {
                return theEnum;
            }
        }
        return No_Error;
    }
}
