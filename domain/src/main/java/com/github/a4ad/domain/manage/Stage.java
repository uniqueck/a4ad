package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.ValueObject;
import lombok.Value;

@Value
public class Stage implements ValueObject<Stage> {

    String value;

    @Override
    public boolean sameValueAs(Stage o) {
        return this.value.equals(o.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage other = (Stage) o;

        return sameValueAs(other);
    }

    @Override
    public String toString() {
        return value;
    }
}
