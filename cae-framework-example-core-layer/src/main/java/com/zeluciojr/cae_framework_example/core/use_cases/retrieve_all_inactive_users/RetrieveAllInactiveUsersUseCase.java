package com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users;

import com.cae.use_cases.authorization.annotations.ProtectedUseCase;
import com.cae.use_cases.specifics.suppliers.SupplierUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.io.outputs.RetrieveAllInactiveUsersUseCaseOutput;

@ProtectedUseCase(scope = {"retrieve-inactives:users"})
public abstract class RetrieveAllInactiveUsersUseCase extends SupplierUseCase<RetrieveAllInactiveUsersUseCaseOutput> {
}
