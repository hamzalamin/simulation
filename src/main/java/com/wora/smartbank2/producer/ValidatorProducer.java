package com.wora.smartbank2.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class ValidatorProducer {

    @Produces
    @ApplicationScoped
    public Validator getValidator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
