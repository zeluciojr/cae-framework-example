package com.zeluciojr.cae_framework_example.core.entities.factories;

import com.cae.entities.EntityFactory;
import com.zeluciojr.cae_framework_example.core.entities.Email;
import com.zeluciojr.cae_framework_example.core.entities.implementations.EmailImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailFactory implements EntityFactory<Email> {

    public static final EmailFactory SINGLETON = new EmailFactory();

    @Override
    public Email makeNewInstance() {
        return new EmailImplementation();
    }
}
