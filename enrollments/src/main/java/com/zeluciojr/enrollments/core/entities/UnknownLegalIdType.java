package com.zeluciojr.enrollments.core.entities;

public class UnknownLegalIdType extends LegalId{

    public static UnknownLegalIdType of(String value){
        var legalId = new UnknownLegalIdType();
        legalId.setValue(value);
        return legalId;
    }


    @Override
    protected boolean checkValidity() {
        return this.getValue() != null;
    }
}
