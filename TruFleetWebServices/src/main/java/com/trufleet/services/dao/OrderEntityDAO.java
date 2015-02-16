package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.OrderEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

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
