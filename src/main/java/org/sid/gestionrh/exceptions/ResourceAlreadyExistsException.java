package org.sid.gestionrh.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
//    public ResourceAlreadyExistsException(String resource,String name,String message) {
//        super(String.format("The %s %s is already exist! %s",resource,name,message));
//    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
