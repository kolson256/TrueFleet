package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.LinehaulEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class LinehaulEntityDAO extends AbstractDAO<LinehaulEntity> {

    private SessionFactory sessionFactory;

    public LinehaulEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public LinehaulEntity findById(int id) {
        return get(id);
    }

    public int create(LinehaulEntity linehaul) {
        return persist(linehaul).getId();
    }

    public LinehaulEntity modifyContact (LinehaulEntity linehaul) {
        return persist(linehaul);
    }

    public void removeContact(LinehaulEntity linehaul){
        currentSession().delete(linehaul);
    }
}
