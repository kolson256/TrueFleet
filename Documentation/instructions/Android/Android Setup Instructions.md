Setting up Android
-------------
####Install Android Studio:
https://developer.android.com/sdk/installing/studio.html

##Genymotion
Follow this link to install and make Genymotion work with Android studio:
http://tleyden.github.io/blog/2013/11/22/android-studio-plus-genymotion-emulator/

The Genymotion device you want to install and use is:
	Google Naxus 7 4.1.1 - API 16 - 800x1280

##Android studio setup
The Application uses jdk1.8.0_05

At a minimum in android SDK manager you need:
Android SDK Tools
Android SDK Platform-tools
Android SDK Build-tools rev. 20


Within Android 4.1.2(API 16):
SDK Platform

Within Extras: 
Google Play Services

I don't recommend running the Android Emulator studio itself, but if you want to try
you must use API 19 Google API Images installed from the SDK Manager as
All prior versions use an older version of Google Play services with no way to update on the device
Note: If you use a different resolution then 800x1280 the UI might not display properly

##Setting up Google Play Services
Go to the following link and download gapps-jb-20120726-signed.zip
https://goo.im/gapps/

Drag and drop the zip file straight into the emulator
Restart the emulator
Open up play store (it should prompt you to login with gmail account, this is needed with google play services)
After logging in play services should update and be working

If it is not make sure the time on the device is set to the current time and make sure
you downloaded the indicated gapps zip file and are running the indicated emulator
as many combinations of the two will not work

If you run into any issues please contact me, it is possible I might have missed something as 
I can not recreate a computer without any of the above installed to test