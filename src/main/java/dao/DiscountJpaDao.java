package dao;

import entity.Discount;
import entity.Dish;

import javax.persistence.EntityManager;
import java.util.List;

public class DiscountJpaDao extends AbstractJpaDao<Discount>{
    public DiscountJpaDao(EntityManager em) {
        super(em);
        criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        root = criteriaQuery.from(Discount.class);
    }
}
