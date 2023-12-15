package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuId;
    private String description;
    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    public Menu() {
    }

    public Menu(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        if (!this.dishes.contains(dish)){
            this.dishes.add(dish);
        }
    }
}
