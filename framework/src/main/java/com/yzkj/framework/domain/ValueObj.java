package com.yzkj.framework.domain;

public abstract class ValueObj {

    public abstract String toString();

    public abstract int hashCode();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ValueObj other = (ValueObj) obj;
        return compareProperties(other);
    }

    public abstract boolean compareProperties(ValueObj other);
}
