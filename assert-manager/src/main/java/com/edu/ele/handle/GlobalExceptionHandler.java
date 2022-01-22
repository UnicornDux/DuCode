package com.edu.ele.handle;

import com.edu.api.exception.ArgumentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ArgumentException.class)
    public Map<String,Object> ArgumentsNotValidHandler(Exception e){
        Map<String,Object> map = new HashMap<>(2);
        map.put("code",400);
        map.put("msg",e.getMessage());
        return map;
    }
}
