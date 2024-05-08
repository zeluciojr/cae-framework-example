package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateNewUserUseCaseOutput {

    private final Long idNewUser;

}
