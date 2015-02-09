package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.ContactEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
public class ContactEntityDAO extends AbstractDAO<ContactEntity> {

    private SessionFactory sessionFactory;

    public ContactEntityDAO(SessionFactory factory) {
        super(factory);
    }


    public ContactEntity findById(int id) {
        return get(id);
    }

    public int create(ContactEntity contact) {
        return persist(contact).getId();
    }

    public ContactEntity modifyContact (ContactEntity contact) {
        return persist(contact);
    }

     public void removeContact(ContactEntity contact){
        currentSession().delete(contact);
    }

}


