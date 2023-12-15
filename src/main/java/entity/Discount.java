package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int discountId;
    @Column(name = "description")
    private String description;
    @Column(name = "discountPercent")
    private int discountPercent;
    @OneToMany(mappedBy = "discount",cascade = CascadeType.ALL)
    private List<Dish> dishes= new ArrayList<>();

    public Discount() {
    }

    public Discount(String description, int discountPercent) {
        this.description = description;
        this.discountPercent = discountPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public List<Dish> getDish() {
        return dishes;
    }

    public void addNewDish(Dish dish) {
        this.dishes.add(dish);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", description='" + description + '\'' +
                ", discountPercent=" + discountPercent +
                ", dishes=" + dishes +
                '}';
    }
}
