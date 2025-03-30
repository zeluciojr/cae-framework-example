package com.zeluciojr.enrollments.assemblers.autofeatures;

import com.cae.autolog.AutologProvider;
import com.cae.autolog.IOLoggingMode;
import com.cae.autonotify.AutonotifyProvider;
import com.cae.trier.autoretry.AutoretryNotifier;
import com.zeluciojr.enrollments.adapters.autofeatures.DefaultAutoretryObserver;
import com.zeluciojr.enrollments.adapters.autofeatures.DefaultLoggerAdapter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AutofeaturesBootstrap {

    public static void run(){
        bootstrapAutolog();
        bootstrapAutonotify();
        bootstrapAutoretryNotifications();
    }

    private static void bootstrapAutolog() {
        AutologProvider.SINGLETON
                .setProvidedInstance(DefaultLoggerAdapter.SINGLETON)
                .setIOLoggingMode(IOLoggingMode.CAE_NATIVE)
                .setUseCasesLoggingIO(true)
                .setPortsLoggingIO(false)
                .setLoggingStackTrace(true)
                .setNumberOfLinesFromStackTrace(50);
    }

    private static void bootstrapAutonotify() {
        AutonotifyProvider.SINGLETON
                .considerLatency(800)
                .considerInternalMappedExceptions()
                .considerUnexpectedExceptions();
    }

    private static void bootstrapAutoretryNotifications() {
        AutoretryNotifier.SINGLETON.subscribe(DefaultAutoretryObserver.SINGLETON);
    }

}
