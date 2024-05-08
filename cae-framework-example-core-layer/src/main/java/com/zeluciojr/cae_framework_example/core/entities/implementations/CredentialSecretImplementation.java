package com.zeluciojr.cae_framework_example.core.entities.implementations;

import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.zeluciojr.cae_framework_example.core.entities.CredentialSecret;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class CredentialSecretImplementation extends CredentialSecret {

    private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final Pattern ALLOWED_PATTERN = Pattern.compile(REGEX);

    @Override
    public void validatePlainValueFormat() {
        if (!ALLOWED_PATTERN.matcher(this.plainValue).matches())
            throw new InputMappedException(
                    "Secret format not allowed",
                    "More details: ALLOWED PATTERN: \"" + REGEX + "\""
            );
    }

    @Override
    public void encryptInternalPlainValue() {
        this.setEncryptedValue(this.encrypt(this.plainValue));
    }

    @Override
    protected String encrypt(String value) {
        var salt = this.getSalt().orElseGet(this::generateSalt);
        //let's pretend it uses the just-generated or already-generated salt to encrypt the value
        return "some placeholder";
    }

    @Override
    public Boolean matchesWith(String someExternalPlainValue) {
        var someExternalEncryptedValue = this.encrypt(someExternalPlainValue);
        return this.encryptedValue.equals(someExternalEncryptedValue);
    }

    @Override
    protected byte[] generateSalt() {
        new SecureRandom().nextBytes(this.salt);
        return this.salt;
    }
}
