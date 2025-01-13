package cn.cuit.utils;

import lombok.Data;

@Data
public class AjaxResult {
    private Boolean success = true;
    private String message = "操作成功！！";
    private Object resultObj;//操作之后返回的额外参数

    public static AjaxResult me() {
        return new AjaxResult();
    }

    /**
     * 链式编程思维
     *
     * @param success
     * @return
     */
    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;//返回当前线程操作的对象
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }
}