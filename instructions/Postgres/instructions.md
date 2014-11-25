Restoring Database Structure with temporary data in PostgresSQL
-------------

####create three Databases
1. TruFleet        
2. TruFleetAdmin
3. TruFleetTest

####Right click on each database and click restore, chose the filename
corresponding to each backup file here

TruFleet - TruFleetSchema.backup
TruFleetTest - TruFleetTest.backup
TruFleetAdmin - TruFleetAdmin.backup

Command line restore
-----------------------
From the PostgreSQL [Documentation][d_src]
[d_src]: http://www.postgresql.org/docs/8.1/static/backup.html#BACKUP-DUMP-RESTORE

````
  psql dbname < infile
````


