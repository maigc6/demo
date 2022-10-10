package cn.edu.cumt.ec.shop.util;

import cn.edu.cumt.ec.shop.entity.Result;
import cn.edu.cumt.ec.shop.exception.ErrorCode;

public class ResultUtil {

    /**
     * 成功方法
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("SUCCESS");
        result.setData(object);
        return result;
    }

    /**
     * 成功但没有返回值
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败方法:使用任意的状态码和消息
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败方法:使用枚举的状态码和消息
     * @param errorCode
     * @return
     */
    public static Result error(ErrorCode errorCode) {
        Result result = new Result();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMessage());
        return result;
    }
}
