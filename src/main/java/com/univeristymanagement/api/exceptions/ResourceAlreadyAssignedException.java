package com.univeristymanagement.api.exceptions;

public class ResourceAlreadyAssignedException extends RuntimeException{

    public ResourceAlreadyAssignedException(String message) {
        super(message);
    }

    public ResourceAlreadyAssignedException( String nameOfAlreadyAssignedResource,long idOfAlreadyAssigned
            , String nameOfResourceAttemptedToAssignTo, long idOfResourceAttemptedToAssignTo) {
        super(String.format("%s with %s  is already assigned to %s with id %s"
                , nameOfAlreadyAssignedResource,idOfAlreadyAssigned,nameOfResourceAttemptedToAssignTo,idOfResourceAttemptedToAssignTo));

    }




    public ResourceAlreadyAssignedException() {
        super();
    }
}