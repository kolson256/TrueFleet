Running Android Application
-------------
If you are using the database backup files you must login with:
username: test password: test

## To assign an order to the user:
install the DHC Chrome app:
https://chrome.google.com/webstore/detail/dhc-resthttp-api-client/aejoelaoggembcahagimdiliamlcdmfm?hl=en
import orderassign.json into the app (You can find this under Github/instructions/Android)
Click send

-> the server must be running
-> Google Play Services must be up to date and you must be logged in through a gmail account
-> You must have logged in to the app (even if the app isn't running presently) so that the app's
GCM registration ID is in the database

You can click on the order once it's assigned in the app to be taken to the order details page

This POST is being sent to the webservice to send a notification to the app that it has an order assigned to it

Alternatively you can use whatever approach you want to assign the order
send a POST request to http://localhost:8080/Notification (or wherever the server is)

With header: Content-Type application/json
Body:
{
 "username": "test",
  "internalId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10"
}
  
The username corresponds to the user you are assigning the order too
internalId corresponds to the id of the order