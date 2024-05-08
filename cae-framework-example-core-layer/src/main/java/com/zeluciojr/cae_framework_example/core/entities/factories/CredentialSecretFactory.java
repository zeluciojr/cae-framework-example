package com.zeluciojr.cae_framework_example.core.entities.factories;

import com.cae.entities.EntityFactory;
import com.zeluciojr.cae_framework_example.core.entities.CredentialSecret;
import com.zeluciojr.cae_framework_example.core.entities.implementations.CredentialSecretImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CredentialSecretFactory implements EntityFactory<CredentialSecret> {

    public static final CredentialSecretFactory SINGLETON = new CredentialSecretFactory();

    @Override
    public CredentialSecret makeNewInstance() {
        return new CredentialSecretImplementation();
    }

}
