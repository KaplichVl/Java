import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<Food> food = new ArrayList<>();
        Map<Food, Integer> itemCount = new HashMap<>();

        boolean countCal = false,
                useSort = false;

        String[] word = args[0].split("/");
        for (int i = 0; i < word.length; i++) {
            switch (word[i]) {
                case "-cal" -> countCal = true;
                case "-sort" -> useSort = true;
                case "Чай" -> food.add(new Tea(word[++i]));
                case "Яблоко" -> food.add(new Apple(5));
                default -> System.out.println();
            }
        };

        if (useSort)
            food.sort(new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return o2.getName().length() - o1.getName().length();
                }
            });


        if (countCal) {
            double cnt = countCaloriesInBreakfast(food);
            System.out.println("num of cal " + cnt);
        }

        for (Food food1 : food) {
            food1.consume();

            if (!itemCount.containsKey(food1))
                itemCount.put(food1, 1);
            else
                itemCount.put(food1, itemCount.get(food1) + 1);
        }
        System.out.println(itemCount);
    }

    private static double countCaloriesInBreakfast(ArrayList<Food> breakfast) {
        double sum = 0;

        for (Food food : breakfast)
            sum += food.calculateCalories(food);

        return sum;
    }
}