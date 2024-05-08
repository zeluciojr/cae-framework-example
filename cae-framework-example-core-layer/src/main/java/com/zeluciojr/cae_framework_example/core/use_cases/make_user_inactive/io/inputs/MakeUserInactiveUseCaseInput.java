package com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.io.inputs;

import com.cae.use_cases.io.UseCaseInput;
import com.cae.use_cases.io.annotations.NotNullInputField;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MakeUserInactiveUseCaseInput extends UseCaseInput {

    @NotNullInputField
    private final Long userId;

}
