package com.zeluciojr.cae_framework_example.core.entities.implementations;

import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.zeluciojr.cae_framework_example.core.entities.CredentialPair;

public class CredentialPairImplementation extends CredentialPair {

    @Override
    public void validateNewCredentialPair() {
        if (this.getCredentialIds().isEmpty())
            throw new InputMappedException("New credential pairs need at least one credential ID to be created.");
        this.getCredentialSecret()
                .orElseThrow(() -> new InputMappedException("New credential pairs need a secret to be created."))
                .validatePlainValueFormat();
    }
}
