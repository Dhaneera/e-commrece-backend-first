package edu.icet.clothify.config;


import org.springframework.context.annotation.Bean;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
