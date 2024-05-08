package com.zeluciojr.cae_framework_example.core.entities.factories;

import com.cae.entities.EntityFactory;
import com.zeluciojr.cae_framework_example.core.entities.CredentialPair;
import com.zeluciojr.cae_framework_example.core.entities.implementations.CredentialPairImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CredentialPairFactory implements EntityFactory<CredentialPair> {

    public static final CredentialPairFactory SINGLETON = new CredentialPairFactory();

    @Override
    public CredentialPair makeNewInstance() {
        return new CredentialPairImplementation();
    }
}
