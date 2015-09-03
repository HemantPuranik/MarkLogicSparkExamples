## MarkLogic Spark Examples ##

Example application that demonstrate how to use [MarkLogic Connector For Hadoop](http://developer.marklogic.com/products/hadoop) with [Apache Spark](http://spark.apache.org/). For the details refer to the following blog on MarkLogic Developer Community [How to use MarkLogic in Apache Spark applications](http://developer.marklogic.com/blog/marklogic-spark-example "How to use MarkLogic in Apache Spark applications"). 

### Prerequisites ###

1. You have Hadoop 2.7.1 or above and Spark 1.3.1 or above  setup. [MarkLogic Connector For Hadoop](http://developer.marklogic.com/products/hadoop) is supported against Hortonworks Data Platform (HDP) and Cloudera Distribution of Hadoop (CDH). Recent releases of HDP and CDH come bundled with Apache Spark.
2. You have MarkLogic 8.0.3 or above installed and running.
3. You have walked through the [Getting Started with the MarkLogic Connector for Hadoop](http://docs.marklogic.com/guide/mapreduce/quickstart?hq=Hadoop%20Connector) and successfully ran the HelloWorld Sample Application within Hadoop environment.

### Build ###

MarkLogic Spark Examples have dependency on marklogic-mapreduce2-2.1.x.jar and marklogic-xcc-8.0.x.jar libraries. Before you build, you need to put these jar files in your local repository for use in your builds, since they don't exist in any public repository like Maven Central. Once you have added these jars in your local repository, pull down the code from [https://github.com/HemantPuranik/MarkLogicSparkExamples](https://github.com/HemantPuranik/MarkLogicSparkExamples) and run 

	mvn package

This will download the other dependencies from public repository and create the file /target/SparkExamples-1.0-SNAPSHOT.jar.

### Setup ###

#####Setup Database#####

1. Unzip the sample data available in sample directory. 
2. Use mlcp to import data within ConsumerComplaints.csv file into a MarkLogic database.
3. Configure the XDBC app server and attach it to this database.
4. Update configuration file marklogic-spark-count.xml available in conf directory with the host, port and user information. 

#####Setup Spark Environment#####

On the machine where you plan to run the spark application,
 
1. Copy the configuration file marklogic-spark-count.xml to your working directory on the machine.
2. Copy the jar file SparkExamples-1.0-SNAPSHOT.jar to the /lib sub-directory within your working directory.

### Usage ###

In order to run MarkLogicWordCount example within Spark environment, navigate to the SPARK Home directory on the machine where you plan to run the spark application and Run spark-submit script as shown below


	./bin/spark-submit --jars $LIBJARS --master local[2] \
						--class com.marklogic.spark.examples.MarkLogicWordCount \
						[my_working_directory]/lib/SparkExamples-1.0-SNAPSHOT.jar \
						[my_working_directory]/marklogic-spark-count.xml \
						hdfs://[target_directory]/mlsparkcount

Successful execution will produce the desired output in the target hdfs location specified in the last parameter.


### Next Steps ###

We plan to add more example applications to this project as we encounter the need for it. If you'd like to know more about MarkLogic architecture see this [article](http://developer.marklogic.com/learn/arch/diagram-101). 
