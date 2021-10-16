package com.example.file_system.dbService.usersDB;

import com.example.file_system.accounts.UserProfile;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDB {
    private final Session session;

    public UsersDB(Session session) throws HibernateException {
        this.session = session;
    }

    public UserProfile getUser(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq("nickName", name)).uniqueResult();
    }

    public long insertUser(UserProfile userProfile) throws HibernateException {
        return (Long) session.save(userProfile);
    }
}
