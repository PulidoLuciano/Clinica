package com.software.backend.persistence.base;

public class NotFoundOnRepositoryException extends Exception{

    public NotFoundOnRepositoryException(String message){
        super(message);
    }

}
