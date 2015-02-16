package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.ApproleEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class ApproleEntityDAO extends AbstractDAO<ApproleEntity> {

    private SessionFactory sessionFactory;

    public ApproleEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public ApproleEntity findById(int id) {
        return get(id);
    }

    public int create(ApproleEntity approle) {
        return persist(approle).getId();
    }

    public ApproleEntity modifyContact (ApproleEntity approle) {
        return persist(approle);
    }

    public void removeContact(ApproleEntity approle){
        currentSession().delete(approle);
    }


}
