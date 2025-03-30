package com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.RolesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.RolesTableRepository;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.RoleRetrievalByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RoleRetrievalByIdPortAdapter extends RoleRetrievalByIdPort {

    public static final RoleRetrievalByIdPort SINGLETON = new RoleRetrievalByIdPortAdapter(
            RolesTableRepository.SINGLETON
    );

    private final RolesTableRepository rolesTableRepository;

    @Override
    protected Optional<Role> executeLogic(UUID input, ExecutionContext correlation) {
        return this.rolesTableRepository.findById(input.toString()).map(RolesTable::getEntity);
    }
}
