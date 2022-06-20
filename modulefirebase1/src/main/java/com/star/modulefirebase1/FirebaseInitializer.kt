package com.star.modulefirebase1

//import android.content.Context
//import android.util.Log
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.FirebaseApp
//import com.google.firebase.messaging.FirebaseMessaging

//class FirebaseInitializer constructor(context: Context) {
//
//
//    init {
//
//        //FirebaseApp.initializeApp(context)
//
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.i("DEBUG", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            Log.i("DEBUG", "token is: $token")
//        })
//
//    }
//
//
//}