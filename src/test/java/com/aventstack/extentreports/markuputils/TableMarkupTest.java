package com.aventstack.extentreports.markuputils;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TableMarkupTest {

    @Test
    public void tableWithNullText() {
        Markup m = MarkupHelper.createTable(null);
        Assert.assertEquals(m.getMarkup(), "");
    }

    @Test
    public void tableWithData() {
        String[][] data = new String[][]{{"h1", "h2"}, {"c1", "c2"}};
        Markup m = MarkupHelper.createTable(data);
        Assert.assertTrue(m.getMarkup().contains("<td>h1</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>h2</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>c1</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>c2</td>"));
        Assert.assertTrue(m.getMarkup().contains("<table"));
        Assert.assertTrue(m.getMarkup().contains("</table>"));
    }

    @Test
    public void tableWithSingleStringField() {
        Markup m = MarkupHelper.toTable("String");
        Assert.assertTrue(m.getMarkup().contains("<td>String</td>"));
        Assert.assertFalse(m.getMarkup().contains("<th>String</th>"));
    }

    @Test
    public void tableWithSingleList() {
        Markup m = MarkupHelper.toTable(Arrays.asList("Anshoo", "Extent", "Klov"));
        Assert.assertTrue(m.getMarkup().contains("<td>Anshoo</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Extent</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Klov</td>"));
    }

    @Test
    public void tableWithSingleSet() {
        Markup m = MarkupHelper.toTable(Arrays.asList("Anshoo", "Extent", "Klov")
                .stream().collect(Collectors.toSet()));
        Assert.assertTrue(m.getMarkup().contains("<td>Anshoo</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Extent</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Klov</td>"));
    }

    @Test
    public void tableWithSingleArray() {
        Markup m = MarkupHelper.toTable(new String[]{"Anshoo", "Extent", "Klov"});
        Assert.assertTrue(m.getMarkup().contains("<td>Anshoo</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Extent</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Klov</td>"));
    }

    @Test
    public void tableWithPojoBeginningEndingTags() {
        Markup m = MarkupHelper.toTable(new Foo());
        Assert.assertTrue(m.getMarkup().contains("<table"));
        Assert.assertTrue(m.getMarkup().contains("</table>"));
    }

    @Test
    public void tableWithPojoHeaders() {
        Markup m = MarkupHelper.toTable(new Foo());
        Assert.assertTrue(m.getMarkup().contains("<th>names</th>"));
        Assert.assertTrue(m.getMarkup().contains("<th>stack1</th>"));
        Assert.assertTrue(m.getMarkup().contains("<th>items</th>"));
        Assert.assertFalse(m.getMarkup().contains("<th>ignored</th>"));
    }

    @Test
    public void tableWithPojoCells() {
        Markup m = MarkupHelper.toTable(new Foo());
        Assert.assertTrue(m.getMarkup().contains("<td>Anshoo</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Extent</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Klov</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Java</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>C#</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Angular</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Item1:Value1</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Item2:Value2</td>"));
        Assert.assertTrue(m.getMarkup().contains("<td>Item3:Value3</td>"));
        Assert.assertFalse(m.getMarkup().contains("<td>Anshoo/Ignore</td>"));
        Assert.assertFalse(m.getMarkup().contains("<td>Extent/Ignore</td>"));
        Assert.assertFalse(m.getMarkup().contains("<td>Klov/Ignore</td>"));
    }
}
