package com.zeluciojr.cae_framework_example.core.entities.implementations;

import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.entities.factories.CredentialIdFactory;

import java.time.LocalDateTime;

public class UserImplementation extends User {

    @Override
    public void becomeActive() {
        this.active = true;
        this.momentBecomingInactive = null;
    }

    @Override
    public void becomeInactive() {
        this.active = false;
        this.momentBecomingInactive = LocalDateTime.now();
    }

    @Override
    public void makeEmailAsCredentialId() {
        var emailAsCredentialId = CredentialIdFactory.SINGLETON.makeNewInstance();
        emailAsCredentialId.setValue(this.getEmail().getAddress());
        this.credentialPair.getCredentialIds().add(emailAsCredentialId);
    }

    @Override
    public void validatePropertiesForNewUser() {
        if (!this.active)
            throw new InputMappedException(
                    "User must be active to be persisted",
                    "More details: call the User::becomeActive before validating or trying to persist"
            );
        this.email.validateAddressFormat();
        this.credentialPair.validateNewCredentialPair();
    }

}
