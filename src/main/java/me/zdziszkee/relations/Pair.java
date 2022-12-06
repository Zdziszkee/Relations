package me.zdziszkee.relations;

import java.util.Objects;

public record Pair<T, V>(T first, V second) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pair<?, ?> pair = (Pair<?, ?>) o;
        if (!Objects.equals(first, pair.first)) {
            return false;
        }
        return Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + "" + first + "," + second + ')';
    }

}
