public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Введен некорректный номер индекса");
        return (T) elements[index];
    }

    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    for (int j = 0; j < numMoved; j++) {
                        elements[i + j] = elements[i + j + 1];
                    }
                }
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    public void addAll(MyArrayList<T> elements) {
        for (int i = 0; i < elements.size(); i++) {
            add(elements.get(i));
        }
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}