package com.trufleet.services.dao;

import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
public class AccountEntityDAO extends AbstractDAO<AccountEntity> {

    private SessionFactory sessionFactory;

    public AccountEntityDAO(SessionFactory factory) {
        super(factory);
    }

    public AccountEntity findById(int id) {
        return get(id);
    }

    public int create(AccountEntity account) {
        return persist(account).getId();
    }

    public AccountEntity modifyContact (AccountEntity account) {
        return persist(account);
    }

    public void removeContact(AccountEntity account){
        currentSession().delete(account);
    }

}
