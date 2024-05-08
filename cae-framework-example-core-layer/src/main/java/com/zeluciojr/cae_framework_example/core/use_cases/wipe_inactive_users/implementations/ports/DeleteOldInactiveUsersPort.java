package com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports;

import com.cae.ports.specifics.consumers.ConsumerPort;

import java.time.LocalDateTime;

public abstract class DeleteOldInactiveUsersPort extends ConsumerPort<LocalDateTime> {
}
