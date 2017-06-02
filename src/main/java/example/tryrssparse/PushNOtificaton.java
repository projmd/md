package example.tryrssparse;

/**
 * Created by redhwan on 2/24/2017.
 */


import android.app.Application;

import com.onesignal.OneSignal;

public class PushNOtificaton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);
    }
}