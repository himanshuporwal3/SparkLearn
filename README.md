# Learn Spark
Spark Learning

##### Creating a Spark ETL application:

###### Steps:

1. Create a Java (jdk 1.8) Maven project

2.  Add the Spark Core , SQL and MLlib maven dependency. (Copy the content from below and paste in **pom.xml**)

   
       

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

