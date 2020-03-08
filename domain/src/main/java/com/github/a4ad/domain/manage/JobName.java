package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.Entity;
import lombok.Value;

@Value
public class JobName implements Entity<JobName> {
    String value;

    @Override
    public boolean sameIdentityAs(JobName other) {
        return other != null && this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobName other = (JobName) o;

        return sameIdentityAs(other);
    }

}
