package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.RouteEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class RouteEntityDAO extends AbstractDAO<RouteEntity> {
    
    private SessionFactory sessionFactory;

    public RouteEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public RouteEntity findById(int id) {
        return get(id);
    }

    public int create(RouteEntity route) {
        return persist(route).getId();
    }

    public RouteEntity modifyContact (RouteEntity route) {
        return persist(route);
    }

    public void removeContact(RouteEntity route){
        currentSession().delete(route);
    }
    
    
    
}
