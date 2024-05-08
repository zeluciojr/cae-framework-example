package com.zeluciojr.cae_framework_example.core.entities;

import com.cae.entities.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class User implements Entity {

    protected Long id;
    protected String name;
    protected Email email;
    protected CredentialPair credentialPair;
    protected boolean active;
    protected LocalDateTime momentBecomingInactive;

    public abstract void becomeActive();
    public abstract void becomeInactive();
    public abstract void makeEmailAsCredentialId();
    public abstract void validatePropertiesForNewUser();
}
