package com.zeluciojr.cae_framework_example.core.entities.implementations;

import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.zeluciojr.cae_framework_example.core.entities.Email;

import java.util.regex.Pattern;

public class EmailImplementation extends Email {

    private static final String REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern ALLOWED_PATTERN = Pattern.compile(REGEX);

    @Override
    public void validateAddressFormat() {
        if (!ALLOWED_PATTERN.matcher(this.address).matches())
            throw new InputMappedException(
                    "Email format not allowed",
                    "More details: ALLOWED PATTERN: \"" + REGEX + "\""
            );
    }
}
