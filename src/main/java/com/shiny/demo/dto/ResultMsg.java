package com.shiny.demo.dto;

import lombok.Data;

@Data
public class ResultMsg {

    private Integer code;

    private String msg;

    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultMsg getSuccessResult(Object data) {
        return new ResultMsg(1, data);
    }

    public static ResultMsg getErrorResult(Object data, String msg) {
        return new ResultMsg(0, msg, data);
    }

}
