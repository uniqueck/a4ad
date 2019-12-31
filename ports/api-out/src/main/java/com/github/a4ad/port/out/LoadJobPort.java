package com.github.a4ad.port.out;

public interface LoadJobPort {

    JobPortModel loadJob(JobIdPortModel jobIdPortModel);

    interface JobPortModel {

        JobIdPortModel getId();

    }

    interface JobIdPortModel {
        Long getValue();
    }

}
