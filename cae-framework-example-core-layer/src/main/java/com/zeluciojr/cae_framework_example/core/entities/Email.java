package com.zeluciojr.cae_framework_example.core.entities;

import com.cae.entities.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Email implements Entity {

    protected String address;

    public abstract void validateAddressFormat();

}
