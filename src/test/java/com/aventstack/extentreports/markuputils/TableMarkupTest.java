package com.aventstack.extentreports.markuputils;

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
		String[][] data = new String[][] { { "h1", "h2" }, { "c1", "c2" } };
		Markup m = MarkupHelper.createTable(data);
		Assert.assertTrue(m.getMarkup().contains("<td>h1</td>"));
		Assert.assertTrue(m.getMarkup().contains("<td>h2</td>"));
		Assert.assertTrue(m.getMarkup().contains("<td>c1</td>"));
		Assert.assertTrue(m.getMarkup().contains("<td>c2</td>"));
		Assert.assertTrue(m.getMarkup().contains("<table"));
		Assert.assertTrue(m.getMarkup().contains("</table>"));
	}

}
