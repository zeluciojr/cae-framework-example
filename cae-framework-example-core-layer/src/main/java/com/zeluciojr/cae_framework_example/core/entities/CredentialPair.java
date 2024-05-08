package com.zeluciojr.cae_framework_example.core.entities;

import com.cae.entities.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class CredentialPair implements Entity {

    protected List<CredentialId> credentialIds;
    protected CredentialSecret credentialSecret;

    public abstract void validateNewCredentialPair();

    public Optional<CredentialSecret> getCredentialSecret(){
        return Optional.ofNullable(this.credentialSecret);
    }

}
