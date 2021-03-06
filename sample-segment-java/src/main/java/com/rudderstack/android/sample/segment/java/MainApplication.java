package com.rudderstack.android.sample.segment.java;

import android.app.Application;

import com.rudderstack.android.sdk.core.RudderClient;
import com.rudderstack.android.sdk.core.RudderConfig;
import com.rudderstack.android.sdk.core.RudderLogger;
import com.rudderstack.android.sdk.core.RudderMessageBuilder;

import java.util.HashMap;
import java.util.Map;

public class MainApplication extends Application {
    private static MainApplication instance;
    private static RudderClient rudderClient;

    private static final String END_POINT_URL = "";
    private static final String WRITE_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();

        RudderConfig config = new RudderConfig.Builder()
                .withEndPointUri(END_POINT_URL)
                .withLogLevel(RudderLogger.RudderLogLevel.VERBOSE)
                .build();

        instance = this;

        rudderClient = new RudderClient.Builder(this, WRITE_KEY)
                .withRudderConfig(config)
                .build();

        RudderClient.with(this).onIntegrationReady("SOME_KEY", new RudderClient.Callback() {
            @Override
            public void onReady(Object instance) {

            }
        });

        RudderClient.setSingletonInstance(rudderClient);

        RudderClient client = RudderClient.getInstance(
                this,
                WRITE_KEY,
                new RudderConfig.Builder()
                        .withEndPointUri(END_POINT_URL)
                        .build()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("test_key_1", "test_value_1");
        Map<String, String> childProperties = new HashMap<>();
        childProperties.put("test_child_key_1", "test_child_value_1");
        properties.put("test_key_2", childProperties);
        if (rudderClient != null) {
            rudderClient.track(
                    new RudderMessageBuilder()
                            .setEventName("test_track_event")
                            .setUserId("test_user_id")
                            .setProperty(properties)
                            .build()
            );
        }
    }

    public static RudderClient getRudderClient() {
        return rudderClient;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
