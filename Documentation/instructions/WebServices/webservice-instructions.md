Setting up Web Services
-----------------


From the TruFleetWebServices folder

````
> gradle clean
````

````
> gradle capsule
````

The Deploy capsule only runs on Linux (assumedly OSX as well).  A bug in capsule prevents running on Windows machine.

From  TruFleetWebServices\build\libs 
you can move TruFleetWebServices-Deploy.jar to another folder, or execute where it is.

````
> java -jar TruFleetWebServices-Deploy.jar
````

On Windows

````
>gradle run
````

is the simpler method.  Although you may need to use 

````
jps
````
to kill the process after running.