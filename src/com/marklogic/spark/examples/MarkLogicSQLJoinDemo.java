package com.marklogic.spark.examples;

import com.marklogic.mapreduce.DocumentInputFormat;
import com.marklogic.mapreduce.DocumentURI;
import com.marklogic.mapreduce.MarkLogicConstants;
import com.marklogic.mapreduce.MarkLogicNode;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import scala.Tuple2;
import static org.apache.spark.sql.functions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by hpuranik on 7/20/2015.
 */
public class MarkLogicSQLJoinDemo {
    private static final Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingTransaction> USASPENDING_TRANSACTION_EXTRACTOR =
            new Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingTransaction>() {
                @Override
                public USASpendingTransaction call(Tuple2<DocumentURI, MarkLogicNode> arg) throws Exception {
                    DocumentURI key = arg._1();
                    MarkLogicNode value = arg._2();
                    USASpendingTransaction transaction = new USASpendingTransaction();
                    if (key != null && value != null && value.get() != null &&
                            value.get().getNodeType() == Node.DOCUMENT_NODE) {
                        Document doc = (Document)value.get();
                        Element docElement = doc.getDocumentElement();
                        NodeList childNodes = docElement.getChildNodes();
                        for(int index=0; index < childNodes.getLength(); index++){
                            transaction.setProperty(childNodes.item(index).getNodeName(),
                                    childNodes.item(index).getTextContent());
                        }
                    } else {
                        System.out.println("Error in FlatMapFunction key: " + key + ", value: " + value);
                    }
                    return transaction;
                }

            };

    private static final Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingVendor> USASPENDING_VENDOR_EXTRACTOR =
            new Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingVendor>() {
                @Override
                public USASpendingVendor call(Tuple2<DocumentURI, MarkLogicNode> arg) throws Exception {
                    DocumentURI key = arg._1();
                    MarkLogicNode value = arg._2();
                    USASpendingVendor vendor = new USASpendingVendor();
                    if (key != null && value != null && value.get() != null &&
                            value.get().getNodeType() == Node.DOCUMENT_NODE) {
                        Document doc = (Document)value.get();
                        Element docElement = doc.getDocumentElement();
                        NodeList childNodes = docElement.getChildNodes();
                        for(int index=0; index < childNodes.getLength(); index++){
                            vendor.setProperty(childNodes.item(index).getNodeName(),
                                    childNodes.item(index).getTextContent());
                        }
                    } else {
                        System.out.println("Error in FlatMapFunction key: " + key + ", value: " + value);
                    }
                    return vendor;
                }

            };

    private static final Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingPlaceOfPerformance> USASPENDING_POP_EXTRACTOR =
            new Function<Tuple2<DocumentURI, MarkLogicNode>, USASpendingPlaceOfPerformance>() {
                @Override
                public USASpendingPlaceOfPerformance call(Tuple2<DocumentURI, MarkLogicNode> arg) throws Exception {
                    DocumentURI key = arg._1();
                    MarkLogicNode value = arg._2();
                    USASpendingPlaceOfPerformance pop = new USASpendingPlaceOfPerformance();
                    if (key != null && value != null && value.get() != null &&
                            value.get().getNodeType() == Node.DOCUMENT_NODE) {
                        Document doc = (Document)value.get();
                        Element docElement = doc.getDocumentElement();
                        NodeList childNodes = docElement.getChildNodes();
                        for(int index=0; index < childNodes.getLength(); index++){
                            pop.setProperty(childNodes.item(index).getNodeName(),
                                    childNodes.item(index).getTextContent());
                        }
                    } else {
                        System.out.println("Error in FlatMapFunction key: " + key + ", value: " + value);
                    }
                    return pop;
                }

            };

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the configuration file full path as argument");
            System.exit(0);
        }

        String transactionsCollection = "fn:collection(\"USASpendingTransaction\")";
        String vendorsCollection = "fn:collection(\"USASpendingVendor\")";
        String placesCollection = "fn:collection(\"USASpendingPlaceOfPerformance\")";



        SparkConf conf = new SparkConf().setAppName("com.marklogic.spark.examples").setMaster("local");
        JavaSparkContext context = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(context);
        sqlContext.setConf("spark.sql.shuffle.partitions", String.valueOf(10));

        Configuration hdConf = new Configuration();
        String configFilePath = args[0];
        System.out.println(configFilePath);
        FileInputStream ipStream = null;
        try {
            ipStream = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hdConf.addResource(ipStream);

        hdConf.set(MarkLogicConstants.DOCUMENT_SELECTOR, transactionsCollection);
        JavaPairRDD<DocumentURI, MarkLogicNode> mlRDD = context.newAPIHadoopRDD(hdConf, DocumentInputFormat.class,
                DocumentURI.class,
                MarkLogicNode.class);
        JavaRDD<USASpendingTransaction> transactions = mlRDD.map(USASPENDING_TRANSACTION_EXTRACTOR);
        DataFrame transactionRDD = sqlContext.applySchema(transactions, USASpendingTransaction.class);
        transactionRDD.registerTempTable("T");
        //DataFrame transactionCount = sqlContext.sql("SELECT COUNT(uniqueTransactionId) as NumberOfTransactions FROM USASpendingTransaction");
        //DataFrame transactionResults = transactionCount.repartition(1);

        hdConf.set(MarkLogicConstants.DOCUMENT_SELECTOR, vendorsCollection);
        mlRDD = context.newAPIHadoopRDD(hdConf, DocumentInputFormat.class, DocumentURI.class, MarkLogicNode.class);
        JavaRDD<USASpendingVendor> vendors = mlRDD.map(USASPENDING_VENDOR_EXTRACTOR);
        DataFrame vendorRDD = sqlContext.applySchema(vendors, USASpendingVendor.class);
        vendorRDD.registerTempTable("V");
        //DataFrame vendorCount = sqlContext.sql("SELECT COUNT(vendorID) as NumberOfTransactions FROM USASpendingVendor");
        //DataFrame vendorResults = vendorCount.repartition(1);

        //transactionResults = transactionResults.unionAll(vendorResults);

        hdConf.set(MarkLogicConstants.DOCUMENT_SELECTOR, placesCollection);
        mlRDD = context.newAPIHadoopRDD(hdConf, DocumentInputFormat.class, DocumentURI.class, MarkLogicNode.class);
        JavaRDD<USASpendingPlaceOfPerformance> places = mlRDD.map(USASPENDING_POP_EXTRACTOR);
        DataFrame popRDD = sqlContext.applySchema(places, USASpendingPlaceOfPerformance.class);
        popRDD.registerTempTable("P");
        //DataFrame popCount = sqlContext.sql("SELECT COUNT(placeOfPerformanceID) as NumberOfTransactions FROM USASpendingPlaceOfPerformance");
        //DataFrame popResults = popCount.repartition(1);

        //transactionResults = transactionResults.unionAll(popResults);

        DataFrame joinVendorTransactionsPOP =
                sqlContext.sql("SELECT V.vendorID, V.vendorName, COUNT(P.placeOfPerformanceID), " +
                                        "COUNT(DISTINCT P.stateCode) as numStates, SUM(T.obligatedAmount) as totalObligation " +
                                "FROM  V LEFT JOIN T ON V.vendorID=T.vendorID " +
                                "LEFT JOIN P ON T.placeOfPerformanceID=P.placeOfPerformanceID " +
                                "GROUP BY V.vendorID, V.vendorName " +
                                "ORDER BY numStates DESC, totalObligation DESC");
        //DataFrame joinVendorTransactionsPOP = sqlContext.sql("SELECT P.placeOfPerformanceID, P.city, P.stateCode, T.uniqueTransactionId, T.obligatedAmount FROM  P LEFT JOIN T ON P.placeOfPerformanceID=T.placeOfPerformanceID");

        DataFrame results = joinVendorTransactionsPOP.repartition(1);
        JavaRDD<Row> rowRdd = results.toJavaRDD();
        rowRdd.saveAsTextFile(args[1]);
    }
}
