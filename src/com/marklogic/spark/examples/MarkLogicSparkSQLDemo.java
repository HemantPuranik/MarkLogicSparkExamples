package com.marklogic.spark.examples;

import com.marklogic.mapreduce.DocumentInputFormat;
import com.marklogic.mapreduce.DocumentURI;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by hpuranik on 7/8/2015.
 */
public class MarkLogicSparkSQLDemo {
    private static final Function<Tuple2<DocumentURI, MarkLogicNode>, CreditCardComplaint> CREDIT_CARD_COMPLAINT_EXTRACTOR =
            new Function<Tuple2<DocumentURI, MarkLogicNode>, CreditCardComplaint>() {
                @Override
                public CreditCardComplaint call(Tuple2<DocumentURI, MarkLogicNode> arg) throws Exception {
                    DocumentURI key = arg._1();
                    MarkLogicNode value = arg._2();
                    CreditCardComplaint complaint = new CreditCardComplaint();
                    if (key != null && value != null && value.get() != null &&
                            value.get().getNodeType() == Node.DOCUMENT_NODE) {
                        Document doc = (Document)value.get();
                        Element docElement = doc.getDocumentElement();
                        NodeList childNodes = docElement.getChildNodes();
                        for(int index=0; index < childNodes.getLength(); index++){
                            complaint.setProperty(childNodes.item(index).getNodeName(),
                                                    childNodes.item(index).getTextContent());
                        }
                    } else {
                        System.out.println("Error in FlatMapFunction key: " + key + ", value: " + value);
                    }
                    return complaint;
                }

            };


    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the configuration file full path as argument");
            System.exit(0);
        }

        SparkConf conf = new SparkConf().setAppName("com.marklogic.spark.examples").setMaster("local");
        JavaSparkContext context = new JavaSparkContext(conf);

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

        JavaPairRDD<DocumentURI, MarkLogicNode> mlRDD = context.newAPIHadoopRDD(hdConf, DocumentInputFormat.class,
                DocumentURI.class,
                MarkLogicNode.class);

        JavaRDD<CreditCardComplaint> ccComplaints = mlRDD.map(CREDIT_CARD_COMPLAINT_EXTRACTOR);
        SQLContext sqlContext = new SQLContext(context);
        sqlContext.setConf("spark.sql.shuffle.partitions", String.valueOf(10));
        DataFrame sqlRDD = sqlContext.applySchema(ccComplaints, CreditCardComplaint.class);
        sqlRDD.registerTempTable("CreditCardComplaints");
        DataFrame resultsRDD = sqlContext.sql("SELECT company, state, COUNT(complaintID) as NumberOfComplaints " +
                                                "FROM CreditCardComplaints " +
                                                "GROUP BY company, state " +
                                                "ORDER BY company, state");
        DataFrame partitionedResults = resultsRDD.repartition(1);
        JavaRDD<Row> rowRdd = partitionedResults.toJavaRDD();
        rowRdd.saveAsTextFile(args[1]);
    }
}
