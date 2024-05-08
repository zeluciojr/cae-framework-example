package com.zeluciojr.cae_framework_example.core.entities.factories;

import com.cae.entities.EntityFactory;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.entities.implementations.UserImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserFactory implements EntityFactory<User> {

    public static final UserFactory SINGLETON = new UserFactory();

    @Override
    public User makeNewInstance() {
        return new UserImplementation();
    }
}
