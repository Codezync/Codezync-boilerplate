package com.codezync.codezync_boilerplate.Utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.codezync.codezync_boilerplate.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseManager {
    private final String TAG = FirebaseManager.class.getSimpleName();
    Context mContext;

    public FirebaseManager(Context context) {
        mContext = context;
    }

    /*
    Method to send push notification
     */
    public void sendNotification(String title, String messageBody, String data, Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constants.PENDING_INTENT_DATA, data);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = mContext.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(mContext, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    mContext.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    /*
    Method to set firebase crashlytics data
     */
    public void setCrashlytics(String phoneNumber, String email, String userName) {

        //Set identfier as the users phone number
        FirebaseCrashlytics.getInstance().setUserId(phoneNumber);

        // Set a key to a string.
        FirebaseCrashlytics.getInstance().setCustomKey(BoilerPlateConstants.FM_KEY_PHONE_NUMBER, phoneNumber);
        FirebaseCrashlytics.getInstance().setCustomKey(BoilerPlateConstants.FM_KEY_EMAIL, email);
        FirebaseCrashlytics.getInstance().setCustomKey(BoilerPlateConstants.FM_KEY_USER_NAME, userName);

    }

    /*
    Method to subscribe firebase topic
     */
    public void subscribeFirebaseTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtil.error(TAG, "subscribeFirebaseTopic Success");
            }
        });
    }

    /*
   Method to unSubscribe firebase topic
    */
    public void unSubscribeFirebaseTopic(String topic) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtil.error(TAG, "unSubscribeFirebaseTopic Success");
            }
        });
    }

    /*
    Method to create logs in firebase
     */
    public static void setFirebaseLog(String message) {
        FirebaseCrashlytics.getInstance().log(message);
    }
}
