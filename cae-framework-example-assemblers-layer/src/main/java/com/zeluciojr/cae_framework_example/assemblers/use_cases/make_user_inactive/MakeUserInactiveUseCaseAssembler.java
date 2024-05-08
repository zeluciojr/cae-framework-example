package com.zeluciojr.cae_framework_example.assemblers.use_cases.make_user_inactive;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.factories.FetchUserByIdForInactivatingItPortAdapterFactory;
import com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.factories.SaveMakingUserInactivePortAdapterFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.MakeUserInactiveUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.MakeUserInactiveUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MakeUserInactiveUseCaseAssembler implements UseCaseAssembler<MakeUserInactiveUseCase> {

    public static final MakeUserInactiveUseCaseAssembler SINGLETON_ASSEMBLER = new MakeUserInactiveUseCaseAssembler();

    public static final MakeUserInactiveUseCase V1;

    static {
        V1 = MakeUserInactiveUseCaseAssembler.initializeV1();
    }

    private static MakeUserInactiveUseCase initializeV1() {
        return new MakeUserInactiveUseCaseImplementation(
                FetchUserByIdForInactivatingItPortAdapterFactory.V1,
                SaveMakingUserInactivePortAdapterFactory.V1
        );
    }

    @Override
    public MakeUserInactiveUseCase getDefaultAssembledInstance() {
        return V1;
    }
}
