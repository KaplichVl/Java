import java.util.Objects;

public class Apple extends Food{
    private int size;

    public Apple(int size) {
        super("Яблоко");
        this.size = size;
    }

    @Override
    public void consume() {
        System.out.println(this + " съедено");
    }

    @Override
    public int calculateCalories(Object arg) {
        return size / 2;
    }

    @Override
    public boolean equals(Object arg) {
        if (this == arg)
            return true;

        if (!(arg instanceof Apple))
            return false;

        Apple other = (Apple) arg;
        return Objects.equals(size, other.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, this.getName());
    }

}
