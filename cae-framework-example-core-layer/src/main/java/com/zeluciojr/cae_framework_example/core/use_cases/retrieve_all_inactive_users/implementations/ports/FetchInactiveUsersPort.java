package com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations.ports;

import com.cae.ports.specifics.suppliers.SupplierPort;
import com.zeluciojr.cae_framework_example.core.entities.User;

import java.util.List;

public abstract class FetchInactiveUsersPort extends SupplierPort<List<User>> {
}
