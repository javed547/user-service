package com.javed.aws.applications.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex){
        ModelAndView mav = new ModelAndView("generic_error");
        mav.addObject("errMsg", "this is Exception.class");
        return mav;
    }

}
