package opt.sai.com.transformation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

import static org.apache.spark.sql.functions.*;

public class SparkApplication_CSVtoDB {

    public static void main(String args[]) {

        //Create a Spark Session
        SparkSession spark = new SparkSession.Builder()
                .appName("CSV to DB")
                .master("local")
                .getOrCreate(); //find more option in SessionBuilder

        spark.sparkContext().setLogLevel("ERROR");

        //get data
        Dataset<Row> df = spark.read()
                .format("csv")
                .option("header", true)
                .load("src/main/resources/name_and_comments.txt");

        df.show();
        //Tranformations
        df = df.withColumn("full_name", concat(df.col("last_name"), lit(", "), df.col("first_name")));

        //Transformation Filter
        df = df.filter(df.col("comment")
                .rlike("\\d+"))
                .orderBy(df.col("last_name").asc());

        df.show(100);

        //Write output to Sink (in this case SQL SERVER)
        String dbConnectionUrl = "jdbc:sqlserver://DESKTOP-PN2NT62\\RAKHISQL";
        Properties dbProp = new Properties();
        dbProp.setProperty("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dbProp.setProperty("user", "tutorial");
        dbProp.setProperty("database","SPARKDB");
        dbProp.setProperty("password", "mambo1234");
        df.write()
                .mode(SaveMode.Overwrite)
                .jdbc(dbConnectionUrl, "project1", dbProp);


        //nifi receiver
//        SiteToSiteClientConfig config = new SiteToSiteClient.Builder()
//                .url("http://localhost:8080/nifi")
//                .portName("Data For Spark")
//                .buildConfig();
    }
}
