package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.OrderEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class OrderEntityDAO extends AbstractDAO<OrderEntity> {

    private SessionFactory sessionFactory;

    public OrderEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public OrderEntity findById(int id) {
        return get(id);
    }

    public List<OrderEntity> listByAccount(int id){
        return currentSession().createCriteria(OrderEntity.class)
                .add(Restrictions.eq("accountid", new Integer(id)) )
                .list();
    }

    public List<OrderEntity> listByContact(int id){
        return currentSession().createCriteria(OrderEntity.class)
                .add(Restrictions.eq("contactid", new Integer(id)) )
                .list();
    }
    public int create(OrderEntity order) {
        return persist(order).getId();
    }

    public OrderEntity modifyContact (OrderEntity order) {
        return persist(order);
    }

    public void removeContact(OrderEntity order){
        currentSession().delete(order);
    }
    
}
