package com.github.a4ad.adapter.bpmn;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class BpmnAdapter {

    @Autowired
    private final RuntimeService runtimeService;




}
