package com.zeluciojr.cae_framework_example.spring_controllers.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.assemblers.use_cases.create_new_user.CreateNewUserUseCaseAssembler;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserSpringController {

    private static final CreateNewUserUseCase USE_CASE_V1 = CreateNewUserUseCaseAssembler.V1;
    private static final CreateNewUserUseCase USE_CASE_V2 = CreateNewUserUseCaseAssembler.V2;

    @PostMapping("/v1/users")
    public ResponseEntity<CreateNewUserUseCaseOutput> executeV1(
            @RequestHeader String correlationId,
            @RequestBody CreateNewUserUseCaseInput useCaseInput){
        var useCaseOutput = USE_CASE_V1.execute(useCaseInput, UseCaseExecutionCorrelation.of(correlationId));
        return ResponseEntity.status(201).body(useCaseOutput);
    }

    @PostMapping("/v2/users")
    public ResponseEntity<CreateNewUserUseCaseOutput> executeV2(
            @RequestHeader String correlationId,
            @RequestBody CreateNewUserUseCaseInput useCaseInput){
        var useCaseOutput = USE_CASE_V2.execute(useCaseInput, UseCaseExecutionCorrelation.of(correlationId));
        return ResponseEntity.status(201).body(useCaseOutput);
    }

}
