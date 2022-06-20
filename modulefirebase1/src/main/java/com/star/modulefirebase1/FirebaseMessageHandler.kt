package com.star.modulefirebase1

//import android.util.Log
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage

//class FirebaseMessageHandler : FirebaseMessagingService() {
//
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//
//        Log.i("DEBUG", "onMessageReceived Fired")
//
//        val data = message.data
//
//
//        val title = message.notification?.title
//        val body = message.notification?.body
//
//        Log.i("DEBUG", "title: $title")
//        Log.i("DEBUG", "body: $body")
//
//
//    }
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//
//        Log.i("DEBUG", "device token is: $token")
//    }
//
//    override fun onDeletedMessages() {
//        super.onDeletedMessages()
//
//        Log.i("DEBUG", "onDeletedMessages Fired")
//    }
//
//}