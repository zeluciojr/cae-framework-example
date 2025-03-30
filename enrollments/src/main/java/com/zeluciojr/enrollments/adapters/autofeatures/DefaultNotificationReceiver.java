package com.zeluciojr.enrollments.adapters.autofeatures;

import com.cae.autolog.Logger;
import com.cae.autonotify.Notification;
import com.cae.autonotify.NotificationObserver;
import lombok.RequiredArgsConstructor;

/*
Each time an occurrence happens that match the policy you set in your Autonotify, this component is prepared to
react. Just like in the Autoretry cases, you could simply log the occurrences, make data points with it in your
observability tools or anything else your needs and/or creativity allows you to engineer.
 */
@RequiredArgsConstructor
public class DefaultNotificationReceiver implements NotificationObserver {

    public static final NotificationObserver SINGLETON = new DefaultNotificationReceiver(
            DefaultLoggerAdapter.SINGLETON
    );

    private final Logger logger;

    @Override
    public void getNotified(Notification notification) {
        this.logger.logDebug(notification.toString());
    }

}
