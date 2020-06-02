package com.aventstack.extentreports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.annotations.MarkupIgnore;
import com.aventstack.extentreports.annotations.MarkupInclude;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.TestService;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.filter.ContextFilter;

public class Main {

    public static void main(String[] args) {
        reporters();
    }

    @MarkupInclude
    private String fieldName = "test";
    @MarkupInclude
    private String[] arr = new String[]{"arr1", "arr2"};
    @MarkupInclude
    private String anotherField = "test1";
    @MarkupInclude
    private Map<String, Object> map = new HashMap<>();
    @MarkupInclude
    private List<String> list = new ArrayList<>();
    @MarkupIgnore
    private String anotherField2 = "test2";
    @MarkupIgnore
    private Set<String> set = new HashSet<>();
    private Long l = 0L;
    private String noANnotation = "no";

    public static Main table() {
        Main m = new Main();
        m.map.put("k1", "v1");
        m.map.put("k2", "v2");
        m.list.add("list2");
        m.list.add("list1");
        m.set.add("set1");
        m.set.add("set2");
        System.out.println(MarkupHelper.createOrderedList(m.map).getMarkup());
        System.out.println(MarkupHelper.createOrderedList(m.list).getMarkup());
        System.out.println(MarkupHelper.createOrderedList(m.set).getMarkup());
        System.out.println(MarkupHelper.createUnorderedList(m.map).getMarkup());
        System.out.println(MarkupHelper.createUnorderedList(m.list).getMarkup());
        System.out.println(MarkupHelper.createUnorderedList(m.set).getMarkup());
        System.out.println(MarkupHelper.createTable2(m.arr, "table-responsive").getMarkup());
        System.out.println(MarkupHelper.createTable2(m.map, "table-responsive").getMarkup());
        System.out.println(MarkupHelper.createTable2(m.set, "table-responsive").getMarkup());
        System.out.println(MarkupHelper.createTable2(m.list, new String[]{"table-responsive"}).getMarkup());
        System.out.println(MarkupHelper.createTable2(m, new String[]{"table-responsive"}).getMarkup());
        return m;
    }

    public static void reporters() {
        ExtentReports extent = new ExtentReports();
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark/spark.html");
        spark.config().setOfflineMode(true);
        //spark.withFilter(ContextFilter.builder().status(Status.PASS).build());
        extent.attachReporter(spark);
        extent.createTest("NewTest").assignCategory("Tag1").log(Status.INFO, "log1");
        extent.createTest("Child").createNode("Child").skip("SKIP");
        extent.createTest("NewTest").createNode("GC").createNode("GC").assignCategory("Tag2")
                .fail("Fail");
        Main m = table();
        extent.createTest("Markup").log(Status.INFO, MarkupHelper.createTable2(m));
        extent.flush();
    }

    public static void stats() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark/");
        extent.attachReporter(spark);
        extent.createTest("NewTest1");
        extent.createTest("NewTest2").log(Status.INFO, "log1");
        extent.flush();
        extent.createTest("Parent").createNode("Child").skip("SKIP");
        ExtentTest test = extent.createTest("NewTest");
        test.createNode("C1").createNode("GC1")
                .warning("Fail");
        test.fail("Failed");
        extent.createTest("NewTest2").createNode("C2").createNode("GC2")
                .warning("Fail");
        extent.flush();
    }

    public static void removeTest() {
        Test t1 = Test.builder().name("Keep").build();
        Test t2 = Test.builder().name("Remove").build();
        List<Test> list = new ArrayList<>(Arrays.asList(new Test[]{t1}));
        System.out.println(TestService.deleteTest(list, t2));
        System.out.println(list.size());
    }

    public static void status() {
        List<Status> l = new ArrayList<>();
        l.add(Status.SKIP);
        l.add(Status.RETRY);
        l.add(Status.INFO);
        l.add(Status.PASS);
        l.add(Status.FAIL);
        System.out.println(l);

        System.out.println(Status.min(l).toString());
        System.out.println(Status.max(l));

        List<Status> newList = Status.getResolvedHierarchy(l);
        System.out.println(newList);
        System.out.println(l);
    }

    public static void tags() {
        Category t1 = new Category("tag1");
        Category t2 = new Category("tag2");
        Category t3 = new Category("tag2");

        Test test = new Test();
        Set<Category> set = new HashSet<>();
        set.add(t1);
        set.add(t2);
        set.add(t3);

        test.getCategorySet().stream().map(Category::getName).forEach(System.out::println);

        final List<String> list = new ArrayList<>(2);
        list.add("A");
        list.add("B");
        list.stream().forEach(System.out::println);
    }

}
