package cn.edu.cumt.ec.shop.exception;

/**
 * 错误编码
 */
public enum ErrorCode {
    NAME_EXCEEDED_CHARRACTER_LIMIT(10001, "姓名超过了限制，最大4个字符!"),
    PARAMS_ERROR(10002,"参数错误"),
    JSON_PARSE_ERROR(10003,"JSON解析错误"),
    ILLEAGAL_STRING(10004,"非法字符串"),
    UNKNOW_ERROR(10005,"未知错误");

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
