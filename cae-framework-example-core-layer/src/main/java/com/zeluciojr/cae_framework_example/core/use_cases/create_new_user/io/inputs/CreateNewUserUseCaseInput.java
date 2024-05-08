package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs;

import com.cae.use_cases.io.UseCaseInput;
import com.cae.use_cases.io.annotations.NotBlankInputField;
import com.cae.use_cases.io.annotations.NotNullInputField;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateNewUserUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private final String name;

    @NotNullInputField
    @NotBlankInputField
    private final String email;

    @NotNullInputField
    @NotBlankInputField
    private final String secret;

}
