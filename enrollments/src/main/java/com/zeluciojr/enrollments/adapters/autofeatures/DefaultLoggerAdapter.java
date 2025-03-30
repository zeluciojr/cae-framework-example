package com.zeluciojr.enrollments.adapters.autofeatures;

import com.cae.autolog.Logger;
import lombok.extern.slf4j.Slf4j;


/*
This will adapt any Logger library of your preference to the contract the CAE Framework uses.
Choose any library and use it here, the framework will use it throughout its features via the Autolog configuration.
 */
@Slf4j
public class DefaultLoggerAdapter implements Logger {

    public static final Logger SINGLETON = new DefaultLoggerAdapter();

    @Override
    public void logInfo(String info) {
        log.info(info);
    }

    @Override
    public void logError(String error) {
        log.error(error);
    }

    @Override
    public void logDebug(String info) {
        log.debug(info);
    }
}
