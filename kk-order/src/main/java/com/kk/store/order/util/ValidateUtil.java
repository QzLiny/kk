package com.kk.store.order.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateUtil {
    /**
     * 它是线程安全的
     */
    private static Validator validator;
 
    static { 
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); 
        validator = factory.getValidator(); 
    } 
     
    public static <T> void validate(T t) throws Exception { 
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t); 
        if(constraintViolations.size() > 0) { 
            String validateError = ""; 
            for(ConstraintViolation<T> constraintViolation: constraintViolations) { 
                validateError += constraintViolation.getMessage() + ";"; 
            } 
            throw new Exception(validateError); 
        } 
    } 
     
} 
