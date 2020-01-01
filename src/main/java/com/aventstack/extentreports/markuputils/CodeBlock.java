package com.aventstack.extentreports.markuputils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import freemarker.template.Template;
import freemarker.template.TemplateException;

class CodeBlock extends MarkupTemplate implements Markup {

	private static final long serialVersionUID = -5532095355983830164L;
	private static final AtomicInteger id = new AtomicInteger(0);
	private static final String CODEBLOCK_TEMPLATE = "codeblock.ftl";
	private static final String CODEBLOCK_JSON_TEMPLATE = "codeblock.json.ftl";
	private static Template codeblock;
	private static Template codeblockJson;
	private String code;
	private CodeLanguage lang;

	static {
		try {
			codeblock = ft.createTemplate(CODEBLOCK_TEMPLATE);
			codeblockJson = ft.createTemplate(CODEBLOCK_JSON_TEMPLATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setCodeBlock(String code) {
		this.code = code;
	}

	public String getCodeBlock() {
		return code;
	}

	public void setCodeBlock(String code, CodeLanguage lang) {
		this.code = code;
		this.lang = lang;
	}

	@Override
	public String getMarkup() {
		int index = 0;
		Template t = codeblock;
		if (lang == CodeLanguage.JSON) {
			index = id.getAndIncrement();
			t = codeblockJson;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("index", index);
		try {
			return ft.getSource(t, map);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
