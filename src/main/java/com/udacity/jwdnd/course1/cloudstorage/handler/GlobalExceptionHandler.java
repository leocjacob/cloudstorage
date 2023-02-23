package com.udacity.jwdnd.course1.cloudstorage.handler;

import com.udacity.jwdnd.course1.cloudstorage.exception.StorageFileNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageFileNotFoundException.class)
    public String StorageFileNotFoundExceptionHandler(StorageFileNotFoundException storageFileNotFoundException, Model model, HttpServletRequest httpServletRequest){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode",statusCode);
        }else{
            model.addAttribute("statusCode",404);
        }
        model.addAttribute("message",storageFileNotFoundException.getMessage());
        return "errorx";
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(Model model, HttpServletRequest httpServletRequest) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode",statusCode);
        }else{
            model.addAttribute("statusCode",403);
        }
        model.addAttribute("message","File upload error! Please upload file less than 10 MB.");
        return "errorx";
    }

}
