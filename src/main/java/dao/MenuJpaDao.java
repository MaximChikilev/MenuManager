package dao;

import entity.Menu;

import javax.persistence.EntityManager;
import java.util.List;

public class MenuJpaDao extends AbstractJpaDao<Menu>{
    public MenuJpaDao(EntityManager em) {
        super(em);
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }
}
