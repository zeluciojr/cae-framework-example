package com.zeluciojr.enrollments.adapters.autofeatures;

import com.cae.autolog.Logger;
import com.cae.trier.autoretry.AutoretryNotification;
import com.cae.trier.autoretry.AutoretryObserver;
import lombok.RequiredArgsConstructor;

/*
Each time an Autoretry happens, this component is designed to get notified and do something.
In this case, it is prepared to simply log the occurrence, but you could make it send a data point to an observability
tool such as Datadog, in order to see what parts of your integrations have been failing, even if the failure was
mitigated by retrying locally.
 */
@RequiredArgsConstructor
public class DefaultAutoretryObserver implements AutoretryObserver {

    public static final AutoretryObserver SINGLETON = new DefaultAutoretryObserver(
            DefaultLoggerAdapter.SINGLETON
    );

    private final Logger logger;

    @Override
    public void getNotified(AutoretryNotification autoretryNotification) {
        this.logger.logDebug("Something needed retrying: " + autoretryNotification);
    }

}
