package com.zeluciojr.cae_framework_example.adapters.use_cases.retrieve_all_inactive_users.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.retrieve_all_inactive_users.FetchInactiveUsersPortAdapter;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations.ports.FetchInactiveUsersPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchInactiveUsersPortAdapterFactory {

    public static final FetchInactiveUsersPort V1;

    static {
        V1 = FetchInactiveUsersPortAdapterFactory.initializeV1();
    }

    private static FetchInactiveUsersPort initializeV1() {
        return new FetchInactiveUsersPortAdapter();
    }

}
