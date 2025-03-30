package com.zeluciojr.enrollments.core.use_cases.create_new_person.io.inputs;

import com.cae.autolog.native_io_extraction_mode.json.sensitive.Sensitive;
import com.cae.use_cases.io.UseCaseInput;
import com.cae.use_cases.io.annotations.NotBlankInputField;
import com.cae.use_cases.io.annotations.NotNullInputField;
import lombok.*;

/**
 * This class maps the input fields for your use case. Before executing the use case,
 * the internal method UseCaseInput::validateProperties will be called to ensure each
 * field complies with its requirements.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewPersonUseCaseInput extends UseCaseInput {

    @Sensitive
    @NotBlankInputField
    @NotNullInputField
    private String legalId;

    @NotNullInputField
    @NotBlankInputField
    private String country;

    @NotNullInputField
    @NotBlankInputField
    private String fullName;

    private String preferredName;

}
