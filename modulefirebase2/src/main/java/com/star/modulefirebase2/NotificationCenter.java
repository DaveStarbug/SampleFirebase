package com.star.modulefirebase2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationCenter extends FirebaseMessagingService {

    private static final String TAG = "NotificaitonCenter";
    private static NotificationCenter instance;
    private static MutableLiveData<RemoteMessage> _mldMessage = new MutableLiveData<>();
    public static LiveData<RemoteMessage> ldMessage = _mldMessage;

    private static MutableLiveData<String> _mldToken = new MutableLiveData<>();
    public static LiveData<String> ldToken = _mldToken;

    Context context;

    public static NotificationCenter getInstance() {
        if (instance == null) {
            synchronized (NotificationCenter.class) {
                if (instance == null) {
                    instance = new NotificationCenter();
                }
            }
        }
        return instance;
    }

    public NotificationCenter() {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (!task.isSuccessful()) {

                    Log.i(TAG, "Fetching FCM registration token failed", task.getException());
                } else
                    _mldToken.postValue(task.getResult());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                _mldToken.postValue(e.getMessage());
            }
        });
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        _mldToken.postValue(token);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        _mldMessage.postValue(message);
        //showNotification(message.getData().get("title"), message.getData().get("body"));
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void showNotification(String title, String message) {

        context = getApplicationContext();
        //firebaseActionsListenerCallback.onClickAction(targetActivityName)

        String CHANNEL_ID = "MYCHANNEL";
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                .build();
        NotificationChannel notificationChannel = new
                NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);
        //val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, 0)
        //Uri soundUri = RingtoneManager.getDefaultUri(Notification.DEFAULT_VIBRATE);
        notificationChannel.setSound(null, attributes);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                //.setContentIntent(pendingIntent)
                //.addAction(R.drawable.ic_launcher_foreground, "OPEN", pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSilent(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1, notification);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context, soundUri);
        r.play();
    }
}
