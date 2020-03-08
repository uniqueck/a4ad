package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.ValueObject;
import lombok.Value;

@Value
public class Directory implements ValueObject<Directory> {

    String value;

    Directory(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (value.trim().isEmpty()) {
            this.value = "/";
        } else {
            if (value.trim().endsWith("/")) {
                this.value = value.trim();
            } else {
                this.value = value.trim() + "/";
            }
        }
    }

    @Override
    public boolean sameValueAs(Directory o) {
        return o != null && this.value.equals(o.value);
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

        Directory other = (Directory) o;

        return sameValueAs(other);
    }

    public Directory absolute(Directory directory) {
        if (directory.getValue().startsWith("/")) {
            return new Directory(value + directory.getValue().substring(1));
        } else {
            return new Directory(value + directory.getValue());
        }
    }

    // Script

}
