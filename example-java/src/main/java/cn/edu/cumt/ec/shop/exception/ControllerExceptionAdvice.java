package cn.edu.cumt.ec.shop.exception;

import cn.edu.cumt.ec.shop.entity.Result;
import cn.edu.cumt.ec.shop.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionAdvice {
    Logger logger = LoggerFactory.getLogger(getClass());
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="To show an example of a custom message")
    public Result handlerException(HttpServletRequest request, HttpServletResponse response, CustomException customException){
        logger.error("自定义异常:{}",customException);
        //具体返回什么HttpStatus，可由customException决定
        return ResultUtil.error(customException.getCode(),customException.getMessage());
    }
}
