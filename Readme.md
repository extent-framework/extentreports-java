## ExtentReports [![Join the chat at https://gitter.im/anshooarora/extentreports](https://badges.gitter.im/anshooarora/extentreports.svg)](https://gitter.im/anshooarora/extentreports?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Build Status](https://travis-ci.com/extent-framework/extentreports-java.svg?branch=v5.0.x)](https://travis-ci.com/extent-framework/extentreports-java) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/dbdc8c04b0f84489a738f064f28a82fa)](https://www.codacy.com/app/anshooarora/extentreports?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=extent-framework/extentreports&amp;utm_campaign=Badge_Grade)

## Maven

```
<dependency>
  <groupId>com.aventstack</groupId>
  <artifactId>extentreports</artifactId>
  <version>5.0.0-SNAPSHOT</version>
</dependency>
```

## What's new

#### Custom logs
You can now create your own custom logs, tables with custom headers, pass your POJOs directly
to be converted into a `<table>` etc. You can also specify any CSS classes to be applied on
the table, like in the below example with "table-sm" (a bootstrap table class).

```
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

#### Ordered, Unordered lists
`MarkupHelper::createOrderedList` and `MarkupHelper.createUnorderedList` for quick POJO to HTML list conversion.

```
String[] items = new String[] { "Item1", "Item2", "Item3" };
Set<Object> items = new HashSet<>(Arrays.asList("Item1", "Item2", "Item3"));
List<Object> items = Arrays.asList(new Object[] { "Item1", "Item2", "Item3" });
extent.createTest("Test").info(MarkupHelper.createOrderedList(items));
```
![orderedList](http://extentreports.com/docs/v5/orderedList.png)

```
Map<Object, Object> items = new HashMap<Object, Object>()
{{
     put("Item1", "Value1");
     put("Item2", "Value2");
     put("Item3", "Value3");
}};
extent.createTest("Test").info(MarkupHelper.createUnorderedList(items).getMarkup());
```
![unorderedList](http://extentreports.com/docs/v5/unorderedList.png)

#### Pojo to HTML Table
Pass your pojo to `MarkupHelper::toTable` to convert it into a table structure. Note: this performs only single-level parsing, no deep searches.

```
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

#### Report filters for Status/Tag specific reports

```java
ExtentReports extent = new ExtentReports();
// will only contain failures
ExtentSparkReporter sparkFail = new ExtentSparkReporter("target/spark/fail.html")
  .with()
    .statusFilter().as(new Status[] {Status.FAIL})
  .<ExtentSparkReporter>build();
// will contain all tests
ExtentSparkReporter sparkAll = new ExtentSparkReporter("spark/all.html");
extent.attachReporter(sparkFail, sparkAll);
```

#### View limiting and ordering
The below will limit to 2 views, with DASHBOARD view the primary one, 
followed by TEST. No other views will be displayed. Default setting is to display all views in this 
order: TEST, TAG, EXCEPTION, DASHBOARD.

```
ExtentReports extent = new ExtentReports();
ExtentSparkReporter spark = new ExtentSparkReporter("spark/spark.html")
  .with()
    .viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
  .<ExtentSparkReporter>build();
```

## Breaking changes

* ExtentReports::getStartedReporters removed
* ExtentReports::detachReporter removed
* ExtentReports:setTestRunnerOutput renamed to `addTestRunnerOutput`
* Status::ERROR, Status::FATAL, Status::DEBUG removed
* ExtentHtmlReporter removed
* ExtentLoggerReporter removed

## What's not working (yet)

* OfflineMode
* Append, CreateDomainFromJsonArchive
* Loading external configuration.xml
* ExtentKlovReporter
* You tell me..

## Upcoming

* See [v5.0.x milestones](https://github.com/extent-framework/extentreports-java/issues?q=is%3Aopen+is%3Aissue+milestone%3A5.0.x)
* Want to see a feature added? You can raise one [here](https://github.com/extent-framework/extentreports-java/issues?q=is%3Aopen+is%3Aissue+milestone%3A5.0.x)

## License

Apache-2.0
