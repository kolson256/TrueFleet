package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.ChargeEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class ChargeEntityDAO extends AbstractDAO<ChargeEntity> {

    private SessionFactory sessionFactory;

    public ChargeEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public ChargeEntity findById(int id) {
        return get(id);
    }

    public int create(ChargeEntity charge) {
        return persist(charge).getId();
    }

    public ChargeEntity modifyContact (ChargeEntity charge) {
        return persist(charge);
    }

    public void removeContact(ChargeEntity charge){
        currentSession().delete(charge);
    }

}
