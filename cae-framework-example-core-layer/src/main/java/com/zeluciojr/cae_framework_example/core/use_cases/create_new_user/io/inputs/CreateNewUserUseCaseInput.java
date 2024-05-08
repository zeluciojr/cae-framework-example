package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs;

import com.cae.loggers.native_io_extraction_mode.json.sensitive.Sensitive;
import com.cae.use_cases.io.UseCaseInput;
import com.cae.use_cases.io.annotations.NotBlankInputField;
import com.cae.use_cases.io.annotations.NotNullInputField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateNewUserUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String name;

    @NotNullInputField
    @NotBlankInputField
    private String email;

    @NotNullInputField
    @NotBlankInputField
    @Sensitive
    private String secret;

}
