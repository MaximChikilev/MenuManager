import entity.Dish;
import entity.MenuManager;

public class Main {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        menuManager.fillDataBase();
       System.out.println("There are dishes with discount");
        for (Dish dish:menuManager.getDishesWithDiscount()){
            System.out.println(dish);
        }
        System.out.println("----------------------------------------------------------------------");

        for (Dish dish:menuManager.getDishesInPriceRate()){
            System.out.println(dish);
        }
        System.out.println("----------------------------------------------------------------------");
        menuManager.createOrderWithWeightRestrictions();
    }
}
