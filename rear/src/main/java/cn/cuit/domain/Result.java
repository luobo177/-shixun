package cn.cuit.domain;

import lombok.Data;

@Data
public class Result {
    private Boolean success;
    private String message;
    private Object data;
    private Integer code;

    private Result() {}

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        result.setCode(200);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(message);
        result.setCode(500);
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }
} 