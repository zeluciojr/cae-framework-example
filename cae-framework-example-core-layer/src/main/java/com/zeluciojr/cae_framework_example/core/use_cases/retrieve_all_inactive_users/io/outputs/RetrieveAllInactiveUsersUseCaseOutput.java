package com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.io.outputs;

import com.zeluciojr.cae_framework_example.core.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RetrieveAllInactiveUsersUseCaseOutput {

    private final List<User> inactiveUsers;

}
