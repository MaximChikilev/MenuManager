package dao;

import entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractJpaDao<T> implements Dao<T> {
    protected EntityManager em;
    protected CriteriaBuilder criteriaBuilder;
    protected CriteriaQuery<T> criteriaQuery;
    protected Root<T> root;

    public AbstractJpaDao(EntityManager em) {
        this.em = em;
        this.criteriaBuilder = em.getCriteriaBuilder();
    }

    @Override
    public void create(final T entity) {
        performTransaction(() -> {
            em.persist(entity);
            return null;
        });
    }

    @Override
    public void delete(final T entity) {
        performTransaction(() -> {
            em.remove(entity);
            return null;
        });
    }

    @Override
    public void update(final T entity) {
        performTransaction(() -> {
            em.persist(entity);
            return null;
        });
    }

    @Override
    public List<T> getAll() {
        return em.createQuery(criteriaQuery.select(root)).getResultList();
    }


    protected <T> void performTransaction(Callable<T> action) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            action.call();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive())
                transaction.rollback();
            throw new RuntimeException(ex);
        }
    }
}
