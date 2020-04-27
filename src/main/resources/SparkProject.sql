---- Query:- Sql Server check table exists before creating
--USE SPARKDB
--GO
--IF OBJECT_ID('dbo.PROJECT1', 'U') IS NOT NULL 
--BEGIN
--  PRINT 'Table Exists in SQL Test Database'
--END
--ELSE
--BEGIN
--PRINT 'Table Does not Exists'
--END


USE [SPARKDB]
GO

SELECT [last_name]
      ,[first_name]
      ,[comment]
      ,[full_name]
  FROM [dbo].[project1]
GO

