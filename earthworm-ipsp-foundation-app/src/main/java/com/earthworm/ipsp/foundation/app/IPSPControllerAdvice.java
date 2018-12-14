package com.earthworm.ipsp.foundation.app;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.validate.ValidateHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class IPSPControllerAdvice {
    @InitBinder
    public void bindData(WebDataBinder binder) {
    }

    /**
     * Manual validation failure
     * @param ex
     * @return
     */
    @ExceptionHandler(EWValidationException.class)
    @ResponseBody
    public ResponseEntity<?> ewValidationExceptionHandle(EWValidationException ex) {
        Map<String, String> errMap = ex.getErrorMap();
        if(errMap==null) {
            errMap = new LinkedHashMap<>();
        }

        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * ConstraintViolationException. Automatic verification failure
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<?> constraintViolationExceptionHandle(ConstraintViolationException ex) {
        return new ResponseEntity<>(ValidateHelper.getConstraintViolationExceptionMsg(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EarthWormException.class)
    @ResponseBody
    public ResponseEntity<?> ewExceptionHandle(EarthWormException ex) {
        ResponseEntity<String> entity = new ResponseEntity<String>(ex.getErrMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> ewExceptionHandle(Exception ex) {
        ResponseEntity<String> entity = new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
