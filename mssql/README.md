
# MsSQL & Spring Boot

## 帳密資訊

|item |value
|-|-
|SA Password |sa, UtPJ+b5s956bZA== 
|user |berlin, MuqiawTDfqs7kw==

## 使用 sqlcmd 登入 ms sql

```bash
docker exec -it sql01 bash

/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'UtPJ+b5s956bZA=='
```

## 建立基本的 db
```sql
CREATE DATABASE sql01;
go

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

## mssql 基本指令

SHOW GRANTS

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

DROP TABLE
```sql
USE sql01;
DROP TABLE dbo.my_user;
go
```