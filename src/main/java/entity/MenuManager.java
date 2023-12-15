package entity;

import dao.DiscountJpaDao;
import dao.DishJpaDao;
import dao.MenuJpaDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;


public class MenuManager {
    private Menu menu = new Menu("Base menu");
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MenuManager");
    private EntityManager em = emf.createEntityManager();
    private MenuJpaDao menuJpaDao = new MenuJpaDao(em);
    private DishJpaDao dishJpaDao = new DishJpaDao(em);
    private DiscountJpaDao discountJpaDao = new DiscountJpaDao(em);
    private List<Dish> order = new ArrayList<>();
    private List<Dish> allDishes;
    private int weightRestriction;
    private Map<Integer, Dish> dishMap;


    public void fillDataBase() {
        for (int i = 1; i <= 30; i++) {
            Dish dish = new Dish("Dish #" + i, 50 + 10 * i, 200 + 10 * i);
            dish.setMenu(menu);
            menu.addDish(dish);
        }
        menuJpaDao.create(menu);
        for (int i = 1; i <= 4; i++) {
            Discount discount = new Discount("Discount #" + i, 5 * i);
            for (int y = 1; y < menu.getDishes().size(); y++) {
                if ((y % (i * 2)) == 0) {
                    discount.addNewDish(menu.getDishes().get(y));
                    menu.getDishes().get(y).setDiscount(discount);
                }
            }
            discountJpaDao.create(discount);
        }
    }

    public List<Dish> getDishesWithDiscount() {
        return dishJpaDao.getDishesWithDiscount();
    }

    public List<Dish> getDishesInPriceRate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter min price");
        int minPrice = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter max price");
        int maxPrice = Integer.parseInt(scanner.nextLine());
        scanner.close();
        System.out.println("There are dishes in price rate : ");
        return dishJpaDao.getDishesInPriceRate(minPrice, maxPrice);
    }

    public void createOrderWithWeightRestrictions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter weight restriction");
        weightRestriction = Integer.parseInt(scanner.nextLine());
        allDishes = dishJpaDao.getAll();
        dishMap = getDishMapFromDishList(allDishes);
        System.out.println("All dishes are : ");
        for (Dish dish : allDishes) System.out.println(dish);
        fillOrder();
    }

    private List<Dish> fillOrder() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter dish id to add :");
            System.out.println("Enter stop to finish order :");
            String enter = scanner.nextLine();
            if (!enter.equals("stop")) {
                try {
                    int dishId = Integer.parseInt(enter);
                    if ((getOrderWeight() + dishMap.get(dishId).getDishWeight()) <= weightRestriction) {
                        order.add(dishMap.get(dishId));
                        showDishesInOrder();
                    } else System.out.println("You cannot order this dish. Excess weight");
                } catch (Exception e) {
                    System.out.println("Bad input, try again");
                }
            } else break;
        }
        return order;
    }

    private void showDishesInOrder() {
        System.out.println("You order are : ");
        for (Dish dish : order) System.out.println(dish);
        System.out.println("Total weight is : " + getOrderWeight());

    }

    private int getOrderWeight() {
        int orderWeight = 0;
        for (Dish dish : order) orderWeight += dish.getDishWeight();
        return orderWeight;
    }

    private Map<Integer, Dish> getDishMapFromDishList(List<Dish> list) {
        Map<Integer, Dish> dishMap = new HashMap<>();
        for (Dish dish : list) {
            dishMap.put(dish.getDishId(), dish);
        }
        return dishMap;
    }
}
