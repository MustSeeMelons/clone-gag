package com.strautins.CloneGag.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.constraints.NotNull;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    private void logException(@NotNull Exception e) {
        LOG.debug("GlobalExceptionHandler: " + e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerNotFound(Exception e) {
        logException(e);
        return "redirect:/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleInternal(Exception e) {
        logException(e);
        return "redirect:/500";
    }


}
