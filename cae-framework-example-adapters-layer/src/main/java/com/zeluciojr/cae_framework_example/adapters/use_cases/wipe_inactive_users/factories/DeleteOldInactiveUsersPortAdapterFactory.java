package com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users.DeleteOldInactiveUsersPortAdapter;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.DeleteOldInactiveUsersPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteOldInactiveUsersPortAdapterFactory {

    public static final DeleteOldInactiveUsersPort V1;

    static {
        V1 = DeleteOldInactiveUsersPortAdapterFactory.initializeV1();
    }

    private static DeleteOldInactiveUsersPort initializeV1() {
        return new DeleteOldInactiveUsersPortAdapter();
    }

}
