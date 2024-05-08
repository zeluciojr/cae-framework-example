package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.mappers;

import com.zeluciojr.cae_framework_example.core.entities.CredentialPair;
import com.zeluciojr.cae_framework_example.core.entities.CredentialSecret;
import com.zeluciojr.cae_framework_example.core.entities.Email;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.entities.factories.CredentialPairFactory;
import com.zeluciojr.cae_framework_example.core.entities.factories.CredentialSecretFactory;
import com.zeluciojr.cae_framework_example.core.entities.factories.EmailFactory;
import com.zeluciojr.cae_framework_example.core.entities.factories.UserFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewUserUseCaseInputMapper {


    public static User mapToUserEntity(CreateNewUserUseCaseInput input) {
        var user = UserFactory.SINGLETON.makeNewInstance();
        user.setName(input.getName());
        user.setEmail(CreateNewUserUseCaseInputMapper.mapEmailFrom(input));
        user.setCredentialPair(CreateNewUserUseCaseInputMapper.mapCredentialPairFrom(input));
        return user;
    }

    private static Email mapEmailFrom(CreateNewUserUseCaseInput input) {
        var email = EmailFactory.SINGLETON.makeNewInstance();
        email.setAddress(input.getEmail());
        return email;
    }

    private static CredentialPair mapCredentialPairFrom(CreateNewUserUseCaseInput input) {
        var credentialPair = CredentialPairFactory.SINGLETON.makeNewInstance();
        credentialPair.setCredentialIds(new ArrayList<>());
        credentialPair.setCredentialSecret(CreateNewUserUseCaseInputMapper.mapCredentialSecretFrom(input));
        return credentialPair;
    }

    private static CredentialSecret mapCredentialSecretFrom(CreateNewUserUseCaseInput input) {
        var credentialSecret = CredentialSecretFactory.SINGLETON.makeNewInstance();
        credentialSecret.setPlainValue(input.getSecret());
        return credentialSecret;
    }
}
