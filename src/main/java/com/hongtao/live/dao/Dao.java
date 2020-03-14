package com.hongtao.live.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */
public class Dao {
    private static final String TAG = "Dao";

    private volatile static Dao instance;
    private static SessionFactory sSessionFactory;

    static {
        Configuration config = new Configuration().configure("/hibernate.cfg.xml");
        sSessionFactory = config.buildSessionFactory();
    }

    private Dao() {
    }


    public static Dao getInstance() {
        if (null == instance) {
            synchronized (Dao.class) {
                if (null == instance) {
                    instance = new Dao();
                }
            }
        }
        return instance;
    }

    public Session getSession() {
        return sSessionFactory.openSession();
    }
}
