use master;
RESTORE DATABASE sql01 FROM DISK = '/var/opt/mssql/backup/sql01-001.bak' WITH REPLACE, RECOVERY;
go