package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.ValueObject;
import lombok.Value;

@Value
public class Script implements ValueObject<Script> {

    String value;

    @Override
    public boolean sameValueAs(Script o) {
        return o != null && value.equals(o.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return sameValueAs((Script) o);
    }


}
