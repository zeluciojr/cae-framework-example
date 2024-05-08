package com.zeluciojr.cae_framework_example.adapters.loggers;

import com.cae.loggers.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerAdapter implements Logger {

    /*
    Let's pretend that instead of using the System.out::println, here a real
    Logger library is being used
     */

    public static final Logger SINGLETON = new LoggerAdapter();

    @Override
    public void logInfo(String info) {
        System.out.println(info);
    }

    @Override
    public void logError(String error) {
        System.out.println(error);
    }

    @Override
    public void logDebug(String info) {
        System.out.println(info);
    }
}
