import java.util.Objects;

public class Tea extends Food {
    private String color;

    public Tea(String color) {
        super("Чай");
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    @Override
    public boolean equals(Object arg) {
         if (this == arg)
            return true;

        if (!(arg instanceof Tea))
            return false;

        Tea other = (Tea) arg;
        return Objects.equals(color, other.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(color, this.getName());
    }

    @Override
    public void consume() {
        System.out.println(this + " выпит");
    }

    @Override
    public int calculateCalories(Object arg) {
        return 5;
    }

    @Override
    public String toString() {
        return color + " " + super.toString();
    }
}
