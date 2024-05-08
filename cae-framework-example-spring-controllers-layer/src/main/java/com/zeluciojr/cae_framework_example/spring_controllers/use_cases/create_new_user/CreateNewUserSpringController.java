package com.zeluciojr.cae_framework_example.spring_controllers.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.assemblers.use_cases.create_new_user.CreateNewUserUseCaseAssembler;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class CreateNewUserSpringController {

    private static final CreateNewUserUseCase USE_CASE = CreateNewUserUseCaseAssembler.SINGLETON_ASSEMBLER.getDefaultAssembledInstance();

    @PostMapping
    public ResponseEntity<CreateNewUserUseCaseOutput> execute(
            @RequestHeader String correlationId,
            @RequestBody CreateNewUserUseCaseInput useCaseInput){
        var useCaseOutput = USE_CASE.execute(useCaseInput, UseCaseExecutionCorrelation.of(correlationId));
        return ResponseEntity.status(201).body(useCaseOutput);
    }

}
