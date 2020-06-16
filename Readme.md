## ExtentReports [![Join the chat at https://gitter.im/anshooarora/extentreports](https://badges.gitter.im/anshooarora/extentreports.svg)](https://gitter.im/anshooarora/extentreports?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Maven Central](https://img.shields.io/maven-central/v/com.aventstack/extentreports.svg?maxAge=300)](http://search.maven.org/#search|ga|1|g:"com.aventstack") [![Build Status](https://travis-ci.com/extent-framework/extentreports-java.svg?branch=master)](https://travis-ci.com/extent-framework/extentreports-java) [![CodeFactor](https://www.codefactor.io/repository/github/extent-framework/extentreports-java/badge)](https://www.codefactor.io/repository/github/extent-framework/extentreports-java) [![codecov](https://codecov.io/gh/extent-framework/extentreports-java/branch/master/graph/badge.svg)](https://codecov.io/gh/extent-framework/extentreports-java)

## Maven

```xml
<dependency>
  <groupId>com.aventstack</groupId>
  <artifactId>extentreports</artifactId>
  <version>5.0.0-SNAPSHOT</version>
</dependency>
```

## What's new

### Custom logs
You can now create your own custom logs, tables with custom headers, pass your POJOs directly
to be converted into a `<table>` etc. You can also specify any CSS classes to be applied on
the table, like in the below example with "table-sm" (a bootstrap table class).

```java
public class MyCustomLog {
    private List<Object> names = Arrays.asList("Anshoo", "Extent", "Klov");
    private Object[] favStack = new Object[]{"Java", "C#", "Angular"};
    @MarkupIgnore
    private List<Object> ignored = Arrays.asList("Anshoo/Ignore", "Extent/Ignore", "Klov/Ignore");
    private Map<Object, Object> items = new HashMap<Object, Object>() {
        {
            put("Item1", "Value1");
            put("Item2", "Value2");
            put("Item3", "Value3");
        }
    };
}
extent.createTest("GeneratedLog").generateLog(Status.FAIL, MarkupHelper.toTable(new MyCustomLog(), "table-sm"));
```

![generateLog](http://extentreports.com/docs/v5/generateLog.png)

### Report filters for Status/Tag specific reports
It is now possible to create separate reports for each status (or a group of them). For example, 2 reports can be created per run session to 1. view all tests and 2. view only failed ones. The example below shows just that:

```java
ExtentReports extent = new ExtentReports();
// will only contain failures
ExtentSparkReporter sparkFail = new ExtentSparkReporter("target/spark/fail.html")
  .filter()
    .statusFilter()
    .as(new Status[] { Status.FAIL })
  .apply();
// will contain all tests
ExtentSparkReporter sparkAll = new ExtentSparkReporter("spark/all.html");
extent.attachReporter(sparkFail, sparkAll);
```

### Choose which views to display, in a particular order
It is now possible to select the views and their order. For example: if you want the Dashboard view 
to be the primary view, followed by Tests, you can use the snippet below:

```java
ExtentReports extent = new ExtentReports();
ExtentSparkReporter spark = new ExtentSparkReporter("spark/spark.html")
  .viewConfigurer()
    .viewOrder()
    .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
  .apply();
```

The above will limit the report to 2 views, with DASHBOARD view the primary one, followed by TEST. 
No other views will be displayed. Default setting is to display all views in this order: TEST, 
TAG, EXCEPTION, DASHBOARD.


### Ordered, Unordered lists
Use `MarkupHelper::createOrderedList` or `MarkupHelper.createUnorderedList` for 
quick POJO to HTML list conversion.

```java
String[] items = new String[] { "Item1", "Item2", "Item3" };
Set<Object> items = new HashSet<>(Arrays.asList("Item1", "Item2", "Item3"));
List<Object> items = Arrays.asList(new Object[] { "Item1", "Item2", "Item3" });
extent.createTest("Test").info(MarkupHelper.createOrderedList(items));
```
![orderedList](http://extentreports.com/docs/v5/orderedList.png)

```java
Map<Object, Object> items = new HashMap<Object, Object>()
{{
     put("Item1", "Value1");
     put("Item2", "Value2");
     put("Item3", "Value3");
}};
extent.createTest("Test").info(MarkupHelper.createUnorderedList(items).getMarkup());
```
![unorderedList](http://extentreports.com/docs/v5/unorderedList.png)

### Pojo to HTML Table
Pass your pojo to `MarkupHelper::toTable` to convert it into a table structure. 
Note: this performs only single-level parsing, no deep searches.

```java
public class MyObject {
    private List<Object> names = Arrays.asList("Anshoo", "Extent", "Klov");
    private Object[] favStack = new Object[]{"Java", "C#", "Angular"};
    @MarkupIgnore
    private List<Object> ignored = Arrays.asList("Anshoo/Ignore", "Extent/Ignore", "Klov/Ignore");
    private Map<Object, Object> items = new HashMap<Object, Object>() {
        {
            put("Item1", "Value1");
            put("Item2", "Value2");
            put("Item3", "Value3");
        }
    };
}
extent.createTest("Test").info(MarkupHelper.toTable(new MyObject()));
```

![toTable](http://extentreports.com/docs/v5/toTable.png)

### JSON configuration
Version 4 and earlier had the ability to consume configuration-XML files, a functionality that remained unchanged in v5.0.
External configuration can now be loaded via JSON also, as demonstrated below:

spark-config.json:
```
{
    "theme": "standard",
    "encoding": "utf-8",
    "protocol": "HTTPS",
    "timelineEnabled": false,
    "offlineMode": true,
    "documentTitle": "ExtentReports",
    "reportName": "ExtentReports",
    "timeStampFormat": "MMM dd, yyyy HH:mm:ss a",
    "theme": "STANDARD",
    "js": "",
    "css": ""
}
```

```java
final File CONF = new File("config/spark-config.json");
ExtentSparkReporter spark = new ExtentSparkReporter("target/spark/spark.html");
spark.loadJSONConfig(CONF);
```

### Configuration builders
Until version 4, it was only possible to specify a configuration entry at once, example:

```java
ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
spark.config().setTheme(Theme.DARK);
spark.config().setDocumentTitle("MyReport");
```

Now, it is possible build and pass the entire configuration at once:

```java
spark.withConfig(
  ExtentSparkReporterConfig.builder()
    .theme(Theme.DARK)
    .documentTitle("MyReport")
    .build()
);
```


## Breaking changes

* ExtentReports::getStartedReporters removed
* ExtentReports::detachReporter removed
* ExtentReports:setTestRunnerOutput renamed to `addTestRunnerOutput`
* Status::ERROR, Status::FATAL, Status::DEBUG removed
* ExtentHtmlReporter removed (use ExtentSparkReporter)
* ExtentLoggerReporter removed (use ExtentSparkReporter)
* Reporter::enableTimeline removed, use Reporter::setTimelineEnabled 

## What's not working (yet)

* ~~OfflineMode~~
* Append, CreateDomainFromJsonArchive
* ~~Loading external configuration~~
* You tell me..

## Upcoming

* See [v5.0.x milestones](https://github.com/extent-framework/extentreports-java/issues?q=is%3Aopen+is%3Aissue+milestone%3A5.0.x)
* Want to see a feature added? You can raise one [here](https://github.com/extent-framework/extentreports-java/issues?q=is%3Aopen+is%3Aissue+milestone%3A5.0.x)

## License

Apache-2.0
