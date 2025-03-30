package com.zeluciojr.enrollments.core.entities;

import com.cae.entities.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class LegalId implements Entity {

    private String value;

    public boolean isValid(){
        if (this.getValue() == null || this.getValue().isBlank())
            return false;
        return this.checkValidity();
    }

    protected abstract boolean checkValidity();

}
