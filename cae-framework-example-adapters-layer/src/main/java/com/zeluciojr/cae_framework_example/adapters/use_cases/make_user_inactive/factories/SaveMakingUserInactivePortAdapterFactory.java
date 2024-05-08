package com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive.SaveMakingUserInactivePortAdapter;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.SaveMakingUserInactivePort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveMakingUserInactivePortAdapterFactory {

    public static final SaveMakingUserInactivePort V1;

    static {
        V1 = SaveMakingUserInactivePortAdapterFactory.initializeV1();
    }

    private static SaveMakingUserInactivePort initializeV1() {
        return new SaveMakingUserInactivePortAdapter();
    }

}
