# SampleFirebase

Hi

you can send FCM messages through this API

https://fcm.googleapis.com/fcm/send
Content-Type:application/json
Authorization:key=AIzaSyZ-1u...0GBYzPu7Udno5aA  // You can get the server key (AIzaSyZ-1u...0GBYzPu7Udno5aA),
                                                //from firebase console: Your project -> settings -> Project settings -> Cloud messaging -> Server Key

{"notification" : {
"title": "notification title",
"body" : "notification body"},
"data":{
    "title":"data title",
    "body": "data body"
},
  "to":"fPvpGNFWRN2nhDrgJ3T8-T:APA91bFfktgJpolcUrKc8X2cI5hvQmhUtgpdSHGgwEjdCpMxeMlbrsVOgRVsOl3C1eiXRHyfpt993go_qKC7SMkTYMfivKabWO3ezS0-jZZYy9pOHKoGBk1jq6prRAuGFtCL1TFikRTL"}
  
  
  ======================================
