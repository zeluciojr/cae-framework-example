package com.zeluciojr.cae_framework_example.core.entities;

import com.cae.entities.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public abstract class CredentialSecret implements Entity {

    protected String encryptedValue;
    protected byte[] salt;
    protected String plainValue;

    public Optional<String> getPlainValue(){
        return Optional.ofNullable(this.plainValue);
    }

    public Optional<byte[]> getSalt(){
        return Optional.ofNullable(this.salt);
    }

    public abstract void encryptInternalPlainValue();
    public abstract Boolean matchesWith(String somePlainValue);
    public abstract void validatePlainValueFormat();

    protected abstract String encrypt(String value);
    protected abstract byte[] generateSalt();

}
