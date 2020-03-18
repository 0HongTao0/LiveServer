import com.hongtao.live.dao.entity.UserEntity;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created 2020/3/13.
 *
 * @author HongTao
 */
public class UserDaoTest {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;

    private Logger logger =  LoggerFactory.getLogger(getClass());


    @Before
    public void init() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    //增加
    @Test
    public void insert() {
//        UserEntity ue = new UserEntity();
//        ue.setNick("Anny");
//        ue.setUserId("123");
//        ue.setPassword("123");
//        ue.setBirthday(new Date(946656000000L));
//        session.save(ue);
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", "935245421"));
        List<UserEntity> users = criteria.list();
        logger.warn(users.toString());
        tx.commit();
        session.close();

    }
}