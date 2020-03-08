package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.ValueObject;
import lombok.Value;

@Value
public class JobId implements ValueObject<JobId> {
    Long value;

    @Override
    public boolean sameValueAs(JobId o) {
        return (o != null && this.value.equals(o.getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobId other = (JobId)o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public String toString() {
        return value.toString();
    }

}
