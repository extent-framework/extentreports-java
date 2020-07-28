package com.aventstack.extentreports;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.context.NamedAttributeContext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttribStatusDist<T extends NamedAttribute> {
    private String name;
    private Map<String, Integer> dist = new HashMap<>();

    public AttribStatusDist(NamedAttributeContext<T> context) {
        name = context.getAttr().getName();
        dist = context.getStatusDist().entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toLower(), e -> e.getValue()));
    }
}