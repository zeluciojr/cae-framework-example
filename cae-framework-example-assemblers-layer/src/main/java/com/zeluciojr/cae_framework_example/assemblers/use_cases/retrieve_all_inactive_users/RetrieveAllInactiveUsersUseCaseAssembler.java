package com.zeluciojr.cae_framework_example.assemblers.use_cases.retrieve_all_inactive_users;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.cae_framework_example.adapters.use_cases.retrieve_all_inactive_users.factories.FetchInactiveUsersPortAdapterFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.RetrieveAllInactiveUsersUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations.RetrieveAllInactiveUsersUseCaseImplementation;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RetrieveAllInactiveUsersUseCaseAssembler implements UseCaseAssembler<RetrieveAllInactiveUsersUseCase> {

    public static final RetrieveAllInactiveUsersUseCaseAssembler SINGLETON_ASSEMBLER = new RetrieveAllInactiveUsersUseCaseAssembler();

    public static final RetrieveAllInactiveUsersUseCase V1;

    static {
        V1 = RetrieveAllInactiveUsersUseCaseAssembler.initializeV1();
    }

    private static RetrieveAllInactiveUsersUseCase initializeV1() {
        return new RetrieveAllInactiveUsersUseCaseImplementation(
                FetchInactiveUsersPortAdapterFactory.V1
        );
    }

    @Override
    public RetrieveAllInactiveUsersUseCase getDefaultAssembledInstance() {
        return V1;
    }
}
