package com.github.a4ad.adapter.bpmn.service;

import com.github.a4ad.common.ScopePrototype;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@ScopePrototype
public @interface FlowableJavaDelegate {

    @AliasFor(attribute = "value", annotation = Component.class)
    String value();

}
