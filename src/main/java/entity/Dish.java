package entity;

import javax.persistence.*;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dishId;
    @Column(name = "dish_name")
    private String dishName;
    @Column(name = "dish_cost")
    private int dishCost;
    @Column(name = "dish_weight")
    private int dishWeight;
    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Dish() {
    }

    public Dish(String dishName, int dishCost, int dishWeight) {
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.dishWeight = dishWeight;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishCost() {
        return dishCost;
    }

    public void setDishCost(int dishCost) {
        this.dishCost = dishCost;
    }

    public int getDishWeight() {
        return dishWeight;
    }

    public void setDishWeight(int dishWeight) {
        this.dishWeight = dishWeight;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" + "Id : " + dishId +
                ", Name : '" + dishName + '\'' +
                ", Cost = " + dishCost +
                ", Weight = " + dishWeight + "g" +
                ", Discount = " + ((discount != null) ? discount.getDiscountPercent() : 0) + "%, " +
                menu.getDescription() +
                '}';
    }
}
