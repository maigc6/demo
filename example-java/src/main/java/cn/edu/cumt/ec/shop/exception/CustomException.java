package cn.edu.cumt.ec.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="To show an example of a custom message")
public class CustomException extends RuntimeException {

    private Integer code;

    /**
     * 可以传递任何自定义异常编码
     * @param code
     * @param message
     */
    public CustomException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    /**
     * 只能使用枚举的异常编码
     * @param messageEnum
     */
    public CustomException(ErrorCode messageEnum) {
        super(messageEnum.getMessage());
        this.code = messageEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}