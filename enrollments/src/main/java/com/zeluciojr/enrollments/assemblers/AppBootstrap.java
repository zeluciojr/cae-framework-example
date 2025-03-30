package com.zeluciojr.enrollments.assemblers;

import com.zeluciojr.enrollments.assemblers.autofeatures.AutofeaturesBootstrap;
import com.zeluciojr.enrollments.assemblers.db.DbBootstrap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppBootstrap {

    public static void run(){
        AutofeaturesBootstrap.run();
        DbBootstrap.SINGLETON.startConnection();
    }

}
