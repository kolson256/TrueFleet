package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.LinehaulEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public List<LinehaulEntity> queryLinehaulsByOrderID(int id){
        return currentSession().createCriteria(LinehaulEntity.class)
                .add(Restrictions.eq("orderid", new Integer(id)) )
                .list();
    }

    public List<LinehaulEntity> queryLinehaulsByAccountID(int id){
        Criteria crit =  currentSession().createCriteria(LinehaulEntity.class);
        Criterion rest1 = Restrictions.eq("shipperid", new Integer(id));
        Criterion rest2 = Restrictions.eq("terminalid", new Integer(id));
        Criterion rest3 = Restrictions.eq("receiverid", new Integer(id));
        Criterion rest4 = Restrictions.or(rest1, rest2);
        crit.add(Restrictions.or(rest4, rest3));
        return crit.list();
    }

    public List<LinehaulEntity> queryLinehaulsByRouteID(int id){
        return currentSession().createCriteria(LinehaulEntity.class)
                .add(Restrictions.eq("routeid", new Integer(id)) )
                .list();
    }

    public List<LinehaulEntity> queryLinehaulsByStatus(int id){
        return currentSession().createCriteria(LinehaulEntity.class)
                .add(Restrictions.eq("linehaulstatus", new Integer(id)) )
                .list();
    }

    public List<LinehaulEntity> queryLinehaulByRouteAndOrder(int routeid, int orderid){
        Criteria crit =  currentSession().createCriteria(LinehaulEntity.class);
        Criterion rest1 = Restrictions.eq("routeid", new Integer(routeid));
        Criterion rest2 = Restrictions.eq("orderid", new Integer(orderid));
        return crit.add(Restrictions.and(rest1, rest2)).list();
    }

}
