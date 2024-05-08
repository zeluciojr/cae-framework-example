package com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.FetchUserByIdForInactivatingItPortAdapter;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.FetchUserByIdForInactivatingItPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchUserByIdForInactivatingItPortAdapterFactory {

    public static final FetchUserByIdForInactivatingItPort V1;

    static {
        V1 = FetchUserByIdForInactivatingItPortAdapterFactory.initializeV1();
    }

    private static FetchUserByIdForInactivatingItPort initializeV1() {
        return new FetchUserByIdForInactivatingItPortAdapter();
    }

}
