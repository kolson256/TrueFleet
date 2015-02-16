package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.ContainerEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class ContainerEntityDAO extends AbstractDAO<ContainerEntity> {

    private SessionFactory sessionFactory;

    public ContainerEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public ContainerEntity findById(int id) {
        return get(id);
    }

    public int create(ContainerEntity container) {
        return persist(container).getId();
    }

    public ContainerEntity modifyContact (ContainerEntity container) {
        return persist(container);
    }

    public void removeContact(ContainerEntity container){
        currentSession().delete(container);
    }
    
}
