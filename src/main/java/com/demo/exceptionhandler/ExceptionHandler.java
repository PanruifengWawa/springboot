package com.demo.exceptionhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.enums.ErrorCodeEnum;
import com.demo.exceptions.AuthException;
import com.demo.utils.DataWrapper;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(HttpServletRequest request,
    		HttpServletResponse response,
    		RuntimeException ex) {  
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		dataWrapper.setData(ex.getMessage());
        return dataWrapper;
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AuthException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(HttpServletRequest request,
    		HttpServletResponse response,
    		AuthException ex) {  
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		dataWrapper.setData(ex.getMessage());
        return dataWrapper;
    }

}
