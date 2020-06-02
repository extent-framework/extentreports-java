package com.aventstack.extentreports.observer.entity;

import com.aventstack.extentreports.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class NodeEntity implements ObservedEntity {
    private Test test;
    private Test node;
    @Builder.Default
    private boolean removed = false;
}
