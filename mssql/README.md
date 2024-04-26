
# MsSQL & Spring Boot

## 帳密資訊

|item |value
|-|-
|SA |sa, UtPJ+b5s956bZA== 
|user |berlin, MuqiawTDfqs7kw==
|admin |aster, 8gKP2hN1duFMetrH

## 使用 sqlcmd 登入 ms sql

```bash
docker exec -it --user root sql01 bash

/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'UtPJ+b5s956bZA=='
```

## 建立基本的 db
```sql
-- 建立新的 sysadmin 帳號
USE master;
CREATE LOGIN aster WITH PASSWORD = '8gKP2hN1duFMetrH';
ALTER SERVER ROLE sysadmin ADD MEMBER aster;
go

-- 建立新的 db
CREATE DATABASE sql01;
go

-- 建立專案使用的帳號
USE sql01;
CREATE LOGIN berlin WITH PASSWORD = 'MuqiawTDfqs7kw==';
CREATE USER berlin FOR LOGIN berlin;
GRANT CREATE TABLE TO berlin;
GRANT SELECT, INSERT, UPDATE, DELETE, ALTER, EXECUTE ON SCHEMA::dbo TO berlin;
go

-- security change 
-- REVOKE SELECT, INSERT, UPDATE, DELETE, ALTER, EXECUTE ON SCHEMA::dbo TO berlin;
-- GRANT SELECT, INSERT, UPDATE, DELETE ON SCHEMA::dbo TO berlin;
-- go
```

```bash
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'UtPJ+b5s956bZA==' -i /var/opt/mssql/init/init.sql
```

## 停用 sa 帳號

登入 aster (管理員帳號)

```bash
/opt/mssql-tools/bin/sqlcmd -S localhost -U aster -P '8gKP2hN1duFMetrH'
```

```sql
ALTER LOGIN sa DISABLE;
go

-- 查看 sa 帳號是否停用
SELECT name,is_disabled from sys.server_principals where name='sa'
go
```

## mssql 基本指令

權限查看 (SHOW GRANTS)

```sql
USE sql01;
SELECT 
    dp.permission_name AS [Permission],
    dp.state_desc AS [State]
FROM 
    sys.database_permissions dp
INNER JOIN
    sys.database_principals p ON dp.grantee_principal_id = p.principal_id
WHERE 
    p.name = 'berlin'
go
```

刪表 (DROP TABLE)
```sql
USE sql01;
DROP TABLE dbo.profile;
go
```

備份 (Backup)
```sql
use master;
BACKUP DATABASE sql01 TO DISK='sql01-001.bak'
```

還原 (Restore)
```sql
use master;
RESTORE DATABASE sql01 FROM DISK = 'sql01-001.bak' WITH REPLACE, RECOVERY;
```

載入 SQL (import)
```bash
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P UtPJ+b5s956bZA== -i /var/opt/mssql/backup/dump.sql
```