public interface OrderedList {
    default int getMin() throws IllegalStateException {
        return 0;
    }

    default int getMax() throws IllegalStateException {
        return 0;
    }

    default double getMedian() throws IllegalStateException {
        return 0;
    }

    default void add(int val) {
        return;
    }

    default OrderedList merge(OrderedList other) {
        return null;
    }

    default void remove(int val) {
        return;
    }

    default void removeAll(int cap) {
        return;
    }

    default boolean isEmpty() {
        return false;
    }

    default OrderedList intersection(OrderedList other) {
        return null;
    }

    default OrderedList union(OrderedList other) {
        return null;
    }

    default int size() {
        return 0;
    }
}

