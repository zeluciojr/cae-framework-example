package com.zeluciojr.enrollments.adapters.use_cases.create_new_role;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.RolesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.RolesTableRepository;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.implementations.ports.NewRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewRolePersistencePortAdapter extends NewRolePersistencePort {

    public static final NewRolePersistencePort SINGLETON = new NewRolePersistencePortAdapter(
            RolesTableRepository.SINGLETON
    );

    private final RolesTableRepository rolesTableRepository;

    @Override
    protected void executeLogic(Role input, ExecutionContext correlation) {
        this.rolesTableRepository.createNew(new RolesTable(input));
    }
}
