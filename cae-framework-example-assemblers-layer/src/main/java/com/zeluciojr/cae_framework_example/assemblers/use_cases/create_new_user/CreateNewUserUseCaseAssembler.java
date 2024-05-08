package com.zeluciojr.cae_framework_example.assemblers.use_cases.create_new_user;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.factories.PersistNewUserPortAdapterFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.CreateNewUserUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewUserUseCaseAssembler implements UseCaseAssembler<CreateNewUserUseCase> {

    public static final CreateNewUserUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewUserUseCaseAssembler();

    public static final CreateNewUserUseCase V1;
    public static final CreateNewUserUseCase V2;

    static {
        V1 = CreateNewUserUseCaseAssembler.initializeV1();
        V2 = CreateNewUserUseCaseAssembler.initializeV2();
    }

    private static CreateNewUserUseCase initializeV1() {
        return new CreateNewUserUseCaseImplementation(
                PersistNewUserPortAdapterFactory.V1
        );
    }

    private static CreateNewUserUseCase initializeV2() {
        return new CreateNewUserUseCaseImplementation(
                PersistNewUserPortAdapterFactory.V2
        );
    }

    @Override
    public CreateNewUserUseCase getDefaultAssembledInstance() {
        return V2;
    }
}
