public class MyHashSet<T> {
    private static final int INITIAL_CAPACITY = 16;
    private Object[] elements;
    private int size;

    public MyHashSet() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private int hash(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode()) % elements.length;
    }

    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        if (size == elements.length) {
            resize();
        }
        int index = hash(value);
        while (elements[index] != null) {
            index = (index + 1) % elements.length;
        }
        elements[index] = value;
        size++;
        return true;
    }

    public boolean remove(T value) {
        int index = hash(value);
        int start = index;
        while (elements[index] != null) {
            if (elements[index].equals(value)) {
                elements[index] = null;
                size--;
                rehash(index);
                return true;
            }
            index = (index + 1) % elements.length;
            if (index == start) break;
        }
        return false;
    }

    private void rehash(int emptyIndex) {
        int index = (emptyIndex + 1) % elements.length;
        while (elements[index] != null) {
            T value = (T) elements[index];
            elements[index] = null;
            size--;
            add(value);
            index = (index + 1) % elements.length;
        }
    }

    public boolean contains(T value) {
        int index = hash(value);
        int start = index;
        while (elements[index] != null) {
            if (elements[index].equals(value)) {
                return true;
            }
            index = (index + 1) % elements.length;
            if (index == start) break;
        }
        return false;
    }

    private void resize() {
        Object[] oldElements = elements;
        elements = new Object[oldElements.length * 2];
        size = 0;
        for (Object o : oldElements) {
            if (o != null) {
                add((T) o);
            }
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Object o : elements) {
            if (o != null) {
                if (!first) sb.append(", ");
                sb.append(o);
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
