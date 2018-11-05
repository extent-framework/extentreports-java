package com.aventstack.extentreports.markuputils;

import java.util.concurrent.atomic.AtomicInteger;

class CodeBlock implements Markup {
    
    private static final AtomicInteger id = new AtomicInteger(0);

    private String code;
    private CodeLanguage lang;
    
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
        if (lang == CodeLanguage.JSON) {
            int index = id.getAndIncrement();
            String markup = "<div class='json-tree' id='code-block-json-" + index + "'></div>" +
            "<script>" +
                "function jsonTreeCreate" + index + "() { document.getElementById('code-block-json-" + index + "').innerHTML = JSONTree.create(" + code + "); }" +
                "jsonTreeCreate" + index + "();" +
            "</script>";
            return markup;
        }

        String lhs = "<textarea disabled class='code-block'>";
        String rhs = "</textarea>";
        
        return lhs + code + rhs;
    }

}
