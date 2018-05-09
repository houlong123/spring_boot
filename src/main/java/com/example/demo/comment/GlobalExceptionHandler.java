package com.example.demo.comment;

import com.example.demo.entity.ResponseEntity;
import com.example.demo.exception.JSONException;
import com.example.demo.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error/error";
    public static final String DEFAULT_MYERROR_VIEW = "error/my_error";

    /**
     * 处理指定类型异常，并返回错误视图
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(value = MyException.class)
    public ModelAndView defaultMyErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", "出错了吧，不小心");
        modelAndView.setViewName(DEFAULT_MYERROR_VIEW);
        return modelAndView;
    }

    /**
     * 以RESTful API风格返回错误信息
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = JSONException.class)
    @ResponseBody
    public ResponseEntity defaultJSONErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(ResponseEntity.OK);
        responseEntity.setMessage(e.getMessage());
        responseEntity.setUrl(request.getRequestURL().toString());
        return responseEntity;
    }

}
