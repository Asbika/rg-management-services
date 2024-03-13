package org.sid.gestionrh.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message){
        super(message);
    }
    public ResourceNotFound(String entity, String field, String value){
        super(String.format("%s Not Found With %s:%s",entity,field,value));
    }
    public ResourceNotFound(String entity, String field1, String value1,String field2,String value2){
        super(String.format("%s Not Found With %s:%s And %s:%s",entity,field1,value1,field2,value2));
    }
    public ResourceNotFound(String entity1,String entity2, String field1,String field2, String value1,String value2){
        super(String.format("%s And %d Not Found With %s:%s And %s:%s respectively",entity1,entity2,field1,value1,field2,value2));
    }
}
