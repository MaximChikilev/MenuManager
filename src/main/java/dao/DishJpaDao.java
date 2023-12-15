package dao;

import entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DishJpaDao extends AbstractJpaDao<Dish> {
    public DishJpaDao(EntityManager em) {
        super(em);
        criteriaQuery = criteriaBuilder.createQuery(Dish.class);
        root = criteriaQuery.from(Dish.class);
    }

    public List<Dish> getDishesWithDiscount() {
        criteriaQuery.select(root).
                where(criteriaBuilder.isNotNull(root.get("discount")));
        return em.createQuery(criteriaQuery).getResultList();
    }
    public List<Dish> getDishesInPriceRate(int minPrice,int maxPrice) {
        criteriaQuery.select(root).
                where(criteriaBuilder.between(root.get("dishCost"),minPrice,maxPrice));
        return em.createQuery(criteriaQuery).getResultList();
    }

}
