package com.zeluciojr.cae_framework_example.core.entities.factories;

import com.cae.entities.EntityFactory;
import com.zeluciojr.cae_framework_example.core.entities.CredentialId;
import com.zeluciojr.cae_framework_example.core.entities.implementations.CredentialIdImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CredentialIdFactory implements EntityFactory<CredentialId> {

    public static final CredentialIdFactory SINGLETON = new CredentialIdFactory();

    @Override
    public CredentialId makeNewInstance() {
        return new CredentialIdImplementation();
    }
}
