package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.FreightEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class FreightEntityDAO extends AbstractDAO<FreightEntity> {

    private SessionFactory sessionFactory;

    public FreightEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public FreightEntity findById(int id) {
        return get(id);
    }

    public int create(FreightEntity freight) {
        return persist(freight).getId();
    }

    public FreightEntity modifyContact (FreightEntity freight) {
        return persist(freight);
    }

    public void removeContact(FreightEntity freight){
        currentSession().delete(freight);
    }
    
}
