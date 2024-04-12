package com.patient.service.helper;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException(){
        super("Patient with this id not found in database");
    }

    public PatientNotFoundException(String msg){
        super(msg);
    }

}
