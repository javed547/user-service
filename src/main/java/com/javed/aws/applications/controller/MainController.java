package com.javed.aws.applications.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class.getName());


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView visitHome() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView visitAdmin() {
        ModelAndView model = new ModelAndView("admin");
        logger.debug("render admin page");
        model.addObject("title", "Admministrator Control Panel");
        model.addObject("message", "This page demonstrates how to use Spring security.");

        return model;
    }
}