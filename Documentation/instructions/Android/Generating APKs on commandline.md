Generating apk files on command line (installation files to Android device)
-------------

#### ANDROID_HOME

You will need to set your ANDROID_HOME command for this to work:

set ANDROID_HOME=C:\<installation location>\android-sdk-windows

set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-
tools

#### SDK installation

You will need to have your SDK installed by the components listed in Android Setup instructions

#### Creating apk's
you can generate new apks with: gradlew.bat assembleDebug within /TruefleetAndroid

Apks located at app/build/outputs/apk and include

app-debug.apk and app-debug-unaligned.apk

Anything you run it on must have Google play services installed, be up to date and have a logged in gmail account

To install to genymotion directly just drag and drop both onto genymotion running emulator

Steps indicated in Android Setup Instructions will still need to be taken to get play services on genymotion


####Notes
There will be a conflict if you install on your device or run through Android Emulator as Genymotion has a unique address to access localhost which the application is currently using
If you can access localhost on the computer from your device this can be changed at:
Tasks/WebService
by changing private static final String URL

Note: Screen sizes other then 800x1280 may have bad forming UI



 



