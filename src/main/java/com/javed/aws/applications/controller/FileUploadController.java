package com.javed.aws.applications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class FileUploadController {

    private static final String UPLOAD_DIRECTORY ="/resources/images/";

    @RequestMapping(value = "uploadForm", method = RequestMethod.GET)
    public ModelAndView uploadForm(){
        return new ModelAndView("uploadForm");
    }

    @RequestMapping(value = "saveFile", method = RequestMethod.POST)
    public ModelAndView saveImage(@RequestParam CommonsMultipartFile file, HttpSession httpSession) throws IOException {

        ServletContext context = httpSession.getServletContext();
        String path = context.getRealPath(UPLOAD_DIRECTORY);
        String filename = file.getOriginalFilename();

        System.out.println(path+" "+filename);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                new File(path + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return new ModelAndView("uploadForm","filesuccess","File successfully saved!");
    }

}
