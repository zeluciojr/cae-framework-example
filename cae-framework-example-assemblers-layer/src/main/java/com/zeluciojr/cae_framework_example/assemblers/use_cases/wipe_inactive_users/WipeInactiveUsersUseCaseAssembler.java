package com.zeluciojr.cae_framework_example.assemblers.use_cases.wipe_inactive_users;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.factories.DeleteOldInactiveUsersPortAdapterFactory;
import com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.factories.FetchNumberOfInactiveUsersPortAdapterFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.WipeInactiveUsersUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.WipeInactiveUsersUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WipeInactiveUsersUseCaseAssembler implements UseCaseAssembler<WipeInactiveUsersUseCase> {

    public static final WipeInactiveUsersUseCaseAssembler SINGLETON_ASSEMBLER = new WipeInactiveUsersUseCaseAssembler();

    public static final WipeInactiveUsersUseCase V1;

    static {
        V1 = WipeInactiveUsersUseCaseAssembler.initializeV1();
    }

    private static WipeInactiveUsersUseCase initializeV1() {
        return new WipeInactiveUsersUseCaseImplementation(
                FetchNumberOfInactiveUsersPortAdapterFactory.V1,
                DeleteOldInactiveUsersPortAdapterFactory.V1
        );
    }

    @Override
    public WipeInactiveUsersUseCase getDefaultAssembledInstance() {
        return V1;
    }
}
