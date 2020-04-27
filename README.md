# Learn Spark
Spark Learning

##### Creating a Spark ETL application:

###### Steps:

1. Create a Java (jdk 1.8) Maven project

2.  Add the Spark Core , SQL and MLlib maven dependency. (Copy the content from below and paste in **pom.xml**)


​       

   ```xml
   <!--Spark-->
    	<dependencies>    
   <dependency>
         <groupId>org.apache.spark</groupId>
         <artifactId>spark-core_${scala.version}</artifactId>
         <version>${spark.version}</version>
       </dependency>
   
       <dependency>
         <groupId>org.apache.spark</groupId>
         <artifactId>spark-sql_${scala.version}</artifactId>
         <version>${spark.version}</version>
       </dependency>
   
       <dependency>
         <groupId>org.apache.spark</groupId>
         <artifactId>spark-mllib_${scala.version}</artifactId>
         <version>${spark.version}</version>
       </dependency>
   
       <dependency>
         <groupId>junit</groupId>
         <artifactId>junit</artifactId>
         <version>4.11</version>
         <scope>test</scope>
       </dependency>
       
       <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>${postgresql.version}</version>
     	</dependency>
   </dependencies>
   ```

3. Create the package and add a class. **eg: Spark1App** 
4. Create a java class, under a package named transformation.



##### Spark Programming Model

1. Create a Spark Session using the **SparkSession.Builder**

   ```java
   SparkSession spark = new SparkSession.Builder()
           .appName("CSV to DB")
           .master("local")
           .getOrCreate(); //find more option in SessionBuilder
   ```

   

2. Get data from Source. Spark provides numerous connectors to variety of data sources. 

   ```java
   Dataset<Row> df = spark.read()
           .format("csv")
           .option("header", true)
           .load("src/main/resources/name_and_comments.txt");
   ```

3. Once the data is loaded in data frame, transformation can be applied on it. 

4. Transformations results can be stored in **same data frame** or in different data frame

   ```java
   //Tranformations
   df = df.withColumn("full_name", concat(df.col("last_name"), lit(", "), df.col("first_name")));
   
   //Transformation Filter
   df = df.filter(df.col("comment")
           .rlike("\\d+"))
           .orderBy(df.col("last_name").asc());
   
   df.show(100);
   ```



5. Final / temporary  output can be **written to sink / destination.**

6. In order to best write to a db, it is advisable to write a config file to read the connection string and properties. 

   ```java
   //Eg: to write in SQL Server
   //Write output to Sink (in this case SQL SERVER)
   String dbConnectionUrl = "jdbc:sqlserver://DESKTOP-PN2NT62\\RAKHISQL";
   
   //Connection properties
   Properties dbProp = new Properties();
   dbProp.setProperty("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
   dbProp.setProperty("user", "tutorial");
   dbProp.setProperty("database","SPARKDB");
   dbProp.setProperty("password", "mambo1234");
   
   //write through jdbc connector
   // .jdbc(serverurl, tablename, connection properties);
   df.write()
           .mode(SaveMode.Overwrite)
           .jdbc(dbConnectionUrl, "project1", dbProp);
   ```

