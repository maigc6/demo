package cn.edu.cumt.ec.shop.controller;

import cn.edu.cumt.ec.shop.entity.Result;
import cn.edu.cumt.ec.shop.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController extends AbstractErrorController {
    Logger logger = LoggerFactory.getLogger(getClass());

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.BINDING_ERRORS});
        Map<String, Object> map = getErrorAttributes(request, options);
        //map.put("ext","额外增加的");
        logger.error("系统异常:{}", map);
        return ResultUtil.error((Integer) map.get("status"), (String) map.get("message"));
    }
}
