package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.PersistNewUserPortAdapter;
import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.PersistNewUserPortAdapterV2;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistNewUserPortAdapterFactory{

    public static final PersistNewUserPort V1;
    public static final PersistNewUserPort V2;

    static {
        V1 = PersistNewUserPortAdapterFactory.initializeV1();
        V2 = PersistNewUserPortAdapterFactory.initializeV2();
    }

    private static PersistNewUserPort initializeV1() {
        return new PersistNewUserPortAdapter();
    }

    private static PersistNewUserPort initializeV2() {
        return new PersistNewUserPortAdapterV2();
    }

}
