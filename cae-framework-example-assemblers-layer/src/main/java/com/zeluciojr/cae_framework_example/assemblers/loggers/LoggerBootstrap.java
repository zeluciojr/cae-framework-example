package com.zeluciojr.cae_framework_example.assemblers.loggers;

import com.cae.loggers.IOLoggingMode;
import com.cae.loggers.LoggerProvider;
import com.zeluciojr.cae_framework_example.adapters.loggers.LoggerAdapter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerBootstrap {

    public static void startupLoggerSettings(){
        LoggerProvider.SINGLETON
                .setProvidedInstance(LoggerAdapter.SINGLETON)
                .setIOLoggingMode(IOLoggingMode.CAE_NATIVE)
                .structuredFormat(true)
                .async(true)
                .setUseCasesLoggingIO(true)
                .setPortsLoggingIO(true);
    }

}
