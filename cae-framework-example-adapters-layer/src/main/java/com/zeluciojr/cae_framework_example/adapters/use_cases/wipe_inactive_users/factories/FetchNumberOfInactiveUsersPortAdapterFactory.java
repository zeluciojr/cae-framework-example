package com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.FetchNumberOfInactiveUsersPortAdapter;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.FetchNumberOfInactiveUsersPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchNumberOfInactiveUsersPortAdapterFactory {

    public static final FetchNumberOfInactiveUsersPort V1;

    static {
        V1 = FetchNumberOfInactiveUsersPortAdapterFactory.initializeV1();
    }

    private static FetchNumberOfInactiveUsersPort initializeV1() {
        return new FetchNumberOfInactiveUsersPortAdapter();
    }

}
